package com.Mongo.MongoSpringBoot;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.Mongo.MongoSpringBoot.document.User;
import com.Mongo.MongoSpringBoot.repository.MongoUserRepository;

@SpringBootApplication
public class MongoSpringBootApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MongoSpringBootApplication.class, args);
	}

	@Autowired
	MongoUserRepository userRepo;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("adding details");
		
		userRepo.save(new User(1,"nithy","IT",100000));
		userRepo.save(new User(2,"Anand","IT",200000));
		userRepo.save(new User(3,"kumar","Team Lead",300000));
		userRepo.save(new User(4,"siva","IT")); 
		userRepo.save(new User(5,"Ambani","Businessman"));
		userRepo.save(new User(6,"John","Analyst",200000));
		userRepo.save(new User(7,"David","Busines Analyst",300000));

		ArrayList<User> userList = new ArrayList<User>();
		userList.add(new User(8,"Selva","Full Stack",350000));
		userList.add(new User(9,"seelan","Lead",350000));
		userList.add(new User(10,"Dinesh","Performance Tester",370000));
		userList.add(new User(11,"Balaji","Devops",370000));
		userList.add(new User(12,"Sridhar","Gaming",370000));
		
		userList.add(new User(13,"Selva","Full Stack",350000));
		userList.add(new User(14,"seelan","Lead",350000));
		userList.add(new User(15,"Dinesh","Performance Tester",370000));
		userList.add(new User(16,"Balaji","Devops",370000));
		userList.add(new User(17,"Sridhar","Gaming",370000));
		
		
		userRepo.saveAll(userList);
		
	}
	
 

}
