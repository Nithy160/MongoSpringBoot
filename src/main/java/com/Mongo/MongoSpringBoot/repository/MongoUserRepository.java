package com.Mongo.MongoSpringBoot.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.Mongo.MongoSpringBoot.document.User;

public interface MongoUserRepository extends MongoRepository<User, Integer>{
	
	public List<User> findByTeamNameOrderBySalaryDesc(String team);
	
	

}
