package com.springboot.rest.weservices.restfulwebservices.user;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserResource {
		
	
	  private UserDaoService service;

	public UserResource(UserDaoService service) {
		super();
		this.service = service;
	}
	 
	@GetMapping("/users")
	public List<User> retriewAllUsers(){
		return service.findAll();
	}
	@GetMapping("/users/{id}")
	public User retriewUser(@PathVariable int id){
		return service.findOne(id);
	}
	
	@PostMapping("/users")
	public void addNewUser(@RequestBody User user){
			service.save(user);
	}
}
