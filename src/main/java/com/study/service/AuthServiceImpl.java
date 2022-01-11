package com.study.service;

import com.study.dao.UserInfoRedisDao;
import com.study.domain.UserInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserInfoRedisDao userInfoRedisDao;

    @Autowired
    StringRedisTemplate redisTemplate;


    public UserInfoEntity authInit(String Id){


        ValueOperations<String, String> values = redisTemplate.opsForValue();

        UserInfoEntity userInfoEntity = new UserInfoEntity();

        userInfoEntity.setId(Id);
        userInfoEntity.setAuthorization("Green");


        userInfoRedisDao.save(userInfoEntity);

        Optional<UserInfoEntity> dtp = userInfoRedisDao.findById(userInfoEntity.getId());



        return dtp.orElse(null);
    }

    @Override
    public UserInfoEntity updateGrade_AfterCheck(String Id, String grade) {
        ValueOperations<String, String> values = redisTemplate.opsForValue();

        UserInfoEntity userInfoEntity = new UserInfoEntity();

        userInfoEntity.setId(Id);
        userInfoEntity.setAuthorization(grade);


        userInfoRedisDao.save(userInfoEntity);

        Optional<UserInfoEntity> dtp = userInfoRedisDao.findById(userInfoEntity.getId());



        return dtp.orElse(null);
    }

    @Override
    public UserInfoEntity checkAuth(String Id) {

        Optional<UserInfoEntity> dtp = userInfoRedisDao.findById(Id);

        return dtp.orElse(null);
    }


}
