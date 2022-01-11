package com.study.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.dao.LoginDao;
import com.study.domain.UserEntity;

@Service
public class LoginServiceImpl implements LoginService {

	private final static String REST_API_KEY = "0530ead261a6f23c9a61fdba73622fb7";
	private final static String REDIRECT_URI = "http://localhost:8000/api/kakaoLogin";
	// 적합하지 않다고 판단됨
	// public static HttpSession globalSession;
	@Autowired
	JwtService jwtService;

	@Autowired
	LoginDao loginDao;
	
	@Autowired
	CustomUserDetailService security;

	@Autowired
	AuthService authService;


	public String createMember(String id, String password) throws SQLException {
		UserEntity userEntity = new UserEntity();

		// null이아니면 가입할 이유가 없음
		try {
			UserEntity check_Dto = loginDao.findById(id);
			if (check_Dto != null) {
				check_Dto.getId();
				System.out.println(check_Dto.getId() + "존재함");
				return "존재함";
			}
		} catch (NullPointerException e) {
			throw new NullPointerException("존재하지않음 진행 가능");
			//System.out.println("존재하지않음 진행 가능");


		} catch (SQLException e) {
			throw new SQLException("SQL에러");
			//System.out.println("이도저도 아닌에러");


		}

		password = makeHashPassword(password);

		// null이면 가입시켜줌
		userEntity.setId(id);
		userEntity.setPassword(password);
		authService.authInit(id);
		loginDao.save(userEntity);


		return "SUCCESS";
	}


	@Override
	public String kakaoLoginWithJwt(String authorizationToken) throws SQLException {
		final String RequestUrl = "https://kauth.kakao.com/oauth/token";
		final List<NameValuePair> postParams = new ArrayList<NameValuePair>();
		postParams.add(new BasicNameValuePair("grant_type", "authorization_code"));
		postParams.add(new BasicNameValuePair("client_id", REST_API_KEY)); // REST API KEY
		postParams.add(new BasicNameValuePair("redirect_uri", REDIRECT_URI)); // 리다이렉트 URI
		postParams.add(new BasicNameValuePair("code", authorizationToken)); // 로그인 과정중 얻은 code 값
		final HttpClient client = HttpClientBuilder.create().build();
		final HttpPost post = new HttpPost(RequestUrl);
		JsonNode returnNode = null;
		try {
			post.setEntity(new UrlEncodedFormEntity(postParams));
			final HttpResponse response = client.execute(post);

			if(response.getStatusLine().getStatusCode()!= HttpStatus.SC_OK){
				return null;
			}
			// JSON 형태 반환값 처리
			ObjectMapper mapper = new ObjectMapper();
			returnNode = mapper.readTree(response.getEntity().getContent());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// clear resources
		}

		String jwt = getUserInfo(returnNode);// jwt를 리턴해주지 않아도 생성된것임
		return jwt;
	}

	public String getUserInfo(JsonNode kakao_access_Json) throws SQLException {
		String jwt = null;

		JsonNode accessToken = kakao_access_Json.get("access_token");

		final String RequestUrl = "https://kapi.kakao.com/v2/user/me";
		final HttpClient client = HttpClientBuilder.create().build();
		final HttpPost post = new HttpPost(RequestUrl);

		post.addHeader("Authorization", "Bearer " + accessToken);
		JsonNode returnNode = null;
		try {
			final HttpResponse response = client.execute(post);
			// JSON 형태 반환값 처리
			ObjectMapper mapper = new ObjectMapper();
			returnNode = mapper.readTree(response.getEntity().getContent());


		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}

		String kakao_email = null;
		String kakao_name = null;



		JsonNode profile = returnNode.path("properties");
		JsonNode kakao_account = returnNode.path("kakao_account");
		kakao_email = kakao_account.path("email").asText();
		kakao_name = profile.path("nickname").asText();

		System.out.println(kakao_email);
		System.out.println(kakao_name);



		if (kakao_email != null) {

			createMember(kakao_email, "saltP8"+kakao_email);
			jwt = jwtService.createToken("userInfo", kakao_email, kakao_name);
			// 만들어주면 알아서 서버에저장됨

		}

		System.out.println(jwt);

		return jwt;

	}



	// sha256만들어주는 메소드

	public String makeHashPassword(String pw) {

		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			System.out.println("해쉬형성부 오류");
			e.printStackTrace();
		}
		md.update(pw.getBytes());

		return bytesToHex1(md.digest());

	}

	// sha256 서브 메소드
	public static String bytesToHex1(byte[] bytes) {
		StringBuilder builder = new StringBuilder();
		for (byte b : bytes) {
			builder.append(String.format("%02x", b));
		}

		return builder.toString().substring(5, builder.length() - 1);
	}

}
