package com.study.service;

import com.study.domain.UserInfoEntity;

public interface AuthService {

    public UserInfoEntity authInit(String Id);
    public UserInfoEntity updateGrade_AfterCheck(String Id,String grade);
    public UserInfoEntity checkAuth(String Id);

}
