package com.study.dao;

import java.sql.SQLException;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.domain.UserEntity;

//보통 여기를 아예 클래스로 쓰는구나...
//entity매니저 사용하려면..
@Repository
public interface LoginDao extends JpaRepository<UserEntity, Integer> {


	//primary key로 검색해준다는 의미임, id가
	UserEntity findById(String id) throws SQLException;

}
