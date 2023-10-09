package com.springboot.rest.weservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


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
	public User retriewUser(@PathVariable int id)//@PathVariable is used to get the parameters that we set on the  URL to get the data
	{
		return service.findOne(id);
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@RequestBody User user) //@RequestBody is to get the added data from POST service in talendAPI application
	{ 
		User newUser=service.save(user);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newUser.getId()).toUri();
		return ResponseEntity.created(location).build();
		}
}
