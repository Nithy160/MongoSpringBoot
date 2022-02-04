package com.Mongo.MongoSpringBoot.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {

	@Id
	private Integer id;
	
	private String name;
	
	private String teamName;
	
	private long salary;

	public User(Integer id, String name, String teamName, long salary) {
		super();
		this.id = id;
		this.name = name;
		this.teamName = teamName;
		this.salary = salary;
	}
	public User()
	{}
	public User(Integer id, String name, String teamName) {
		super();
		this.id = id;
		this.name = name;
		this.teamName = teamName;
		//this.salary = salary;
	}
	
	public User( String name, String teamName, long salary) {
		super();
		//this.id = id;
		this.name = name;
		this.teamName = teamName;
		this.salary = salary;
	}
	

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", teamName=" + teamName + ", salary=" + salary + "]";
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}
	
	
}
