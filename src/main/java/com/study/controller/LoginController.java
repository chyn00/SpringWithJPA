package com.study.controller;

import java.net.MalformedURLException;
import java.net.URI;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.study.domain.UserInfoEntity;
import com.study.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.study.service.JwtService;
import com.study.service.LoginService;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@Controller
@RequestMapping("/api")
public class LoginController {

	@Autowired
	LoginService loginService;
	@Autowired
	JwtService jwtService;
	@Autowired
	AuthService authService;


	@RequestMapping(value = "/kakaoLogin")
	public ResponseEntity<Map<String, Object>> kakaoLogin(HttpSession hs,
														  @RequestParam(value = "code", required = false) String authorizationToken) throws MalformedURLException, SQLException {
		System.out.println("kakao");

		String jwt = loginService.kakaoLoginWithJwt(authorizationToken);
		if(jwt==null){
			HttpHeaders headers=new HttpHeaders();
			headers.setLocation(URI.create("http://localhost:8080/"));
			return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
		}
		HttpHeaders headers=new HttpHeaders();
		headers.setLocation(URI.create("http://localhost:8080/"));
		headers.add("jwt", jwt);


		return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
	}

	@ResponseBody
	@RequestMapping(value = "/check")
	public UserInfoEntity df(@RequestParam(value = "id", required = false) String id)  {



		return authService.checkAuth(id);
	}





}
