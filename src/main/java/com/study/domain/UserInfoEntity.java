package com.study.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;


@RedisHash("UserInfo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoEntity {

    @Id
    String id;

    String Authorization;



}


