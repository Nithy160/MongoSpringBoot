package com.Mongo.MongoSpringBoot;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Mongo.MongoSpringBoot.document.User;
import com.Mongo.MongoSpringBoot.repository.MongoUserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

	private final Logger log= LoggerFactory.getLogger(this.getClass());
	@Autowired
	MongoUserRepository userRepo;
	@Autowired
	MongoTemplate mongoTemplate;

	@GetMapping("/all")
	public List<User>	getAllUsers()
	{
		List<User> users= userRepo.findAll();
		return users;
	}

	@GetMapping("/findWith")
	public List<User> findWithFields(@RequestParam(name="name", required=false) String name, 
			@RequestParam(name="teamName", required=false) String teamName,
			@RequestParam( name="salary",required=false) Long salary,
			@RequestParam(name="size", required=false) Integer size,
			@RequestParam(name="page", required=false) Integer page
			)
	{
		Query query = new Query();

		Pageable pageable = PageRequest.of(page != null ? page : 0, size != null ? size : 10);
		
		query.with(pageable);

		if(name!=null)
		{
			query.addCriteria(Criteria.where("name").is(name));
		}
		if(teamName!=null)
		{
			query.addCriteria(Criteria.where("teamName").is(teamName));
		}
		if(salary!=null && salary!=0)
		{
			query.addCriteria(Criteria.where("salary").is(salary));
		}


		List<User> users= mongoTemplate.find(query, User.class);

		return users;
	}



	@GetMapping("/first/{count}")
	public List<User>	getFirst(@PathVariable int count)
	{
		Pageable page= PageRequest.of(0, count);
		Query query= new Query();
		query.addCriteria(Criteria.where("salary").gt(20000)).with(page).with(Sort.by(Sort.Direction.DESC, "salary"));
		List<User> users= mongoTemplate.find(query, User.class);

		return users;
	}

	@PostMapping("/save")
	public User addUser(@RequestBody User user)
	{
		Query query= new Query();
		query.with(Sort.by(Sort.Direction.DESC,"id")).limit(1);
		User maxUser =   mongoTemplate.findOne(query, User.class);
		user.setId(maxUser.getId()+1);

		return 	userRepo.save(user);
	}

	@PutMapping("/update/{userId}")
	public User	updateUser(@RequestBody User user, @PathVariable int userId)
	{

		User existingUser = mongoTemplate.findById(userId, User.class);
		System.out.println("existing user details:"+existingUser.toString());
		Query query= new Query().addCriteria(Criteria.where("id").is(userId));

		Update updateFields = new Update();
		updateFields.set("name", user.getName())
		.set("salary", user.getSalary())
		.set("teamName", user.getTeamName());

		mongoTemplate.updateFirst(query, updateFields, User.class);
		User updatedUser = mongoTemplate.findById(userId, User.class);

		return updatedUser;
	}



	@GetMapping("/userByName/{name}")
	public List<User>	getUserByName(@PathVariable String name)
	{
		Query query= new Query();
		query.addCriteria(Criteria.where("name").is(name));
		List<User> users= mongoTemplate.find(query, User.class);


		return users;
	}



	@GetMapping("/userByTeam/{team}")
	public List<User>	getUserByTeam(@PathVariable String team)
	{		 
		List<User> users= userRepo.findByTeamNameOrderBySalaryDesc(team);
		return users;
	}

	@GetMapping("/{id}")
	public ResponseEntity<User>	getUser(@PathVariable("id") int id)
	{
		Optional<User> user= userRepo.findById(id);

		if(user.isPresent())
			return  new ResponseEntity<>(user.get(),HttpStatus.FOUND);

		else			
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
	}


	@GetMapping("/salaryMoreThan/{salary}")
	public List<User>	groupByField(@PathVariable long salary)
	{	 
		Query query = new Query();
		query.addCriteria(Criteria.where("salary").gt(salary));

		List<User> users= mongoTemplate.find(query, User.class);

		return users;
	}



}
