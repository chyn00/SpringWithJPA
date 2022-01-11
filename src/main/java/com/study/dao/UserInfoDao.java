package com.study.dao;

import com.study.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;


@Repository
public class UserInfoDao {
    @Autowired
    EntityManager entityManager;

    @Transactional
    public void save(UserEntity userEntity){

        entityManager.persist(userEntity);
        //entityManager.merge(userEntity); //비추 전체 변경됨
    }

    public UserEntity findById(String id){

        return entityManager.find(UserEntity.class, id);
    }



}
