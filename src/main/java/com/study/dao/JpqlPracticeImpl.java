package com.study.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.study.domain.UserEntity;


@Repository
public class JpqlPracticeImpl implements JpqlPractice<UserEntity> {
	private static final long serialVersionUID = 1L;
	
	private EntityManager entityManager;
	
	public JpqlPracticeImpl() {
		super();
	}
	
	public JpqlPracticeImpl(EntityManager manager) {
		entityManager = manager;
	}
	
	@Override
	public List<UserEntity> getAll() {
		//jpql을 사용하고 있는것임
		Query query = entityManager.createQuery("from member");
		List<UserEntity> list = query.getResultList();
		entityManager.close();
		return list;
	}

}
