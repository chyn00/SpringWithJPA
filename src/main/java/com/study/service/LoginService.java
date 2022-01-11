package com.study.service;

import java.sql.SQLException;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface LoginService {


	String kakaoLoginWithJwt(String authorizationToken) throws SQLException;

}
