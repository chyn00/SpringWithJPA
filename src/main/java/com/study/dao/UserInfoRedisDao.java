package com.study.dao;

import com.study.domain.UserInfoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserInfoRedisDao  extends CrudRepository<UserInfoEntity, String> {

    public Optional<UserInfoEntity> findById(String id);
}


