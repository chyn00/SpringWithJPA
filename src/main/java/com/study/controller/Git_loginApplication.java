package com.study.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@SpringBootApplication
@ComponentScan("com.*")
@EntityScan(basePackages = {"com.study.domain"})
@EnableJpaRepositories(basePackages = {"com.study.dao"})
public class Git_loginApplication {

	@PersistenceContext
	private EntityManager entityManager;

	public static void main(String[] args) {
		SpringApplication.run(Git_loginApplication.class, args);
	}

}
