package com.Mongo.MongoSpringBoot;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.Mongo.MongoSpringBoot.repository.MongoUserRepository;

@Configuration
@EnableMongoRepositories(basePackageClasses=MongoUserRepository.class)
public class MongoConfig {
	
 

}
