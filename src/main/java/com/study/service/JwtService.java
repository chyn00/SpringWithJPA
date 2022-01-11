package com.study.service;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public interface JwtService {

	String createToken(String subject, String email, String nickName);

	Map<String, Object> verifyJWT(String jwt) throws UnsupportedEncodingException;

	public String getIdByJWT(String jwt) throws UnsupportedEncodingException;

}
