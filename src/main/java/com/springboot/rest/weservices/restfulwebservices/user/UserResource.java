package com.springboot.rest.weservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;


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
	public EntityModel<User> retriewUser(@PathVariable int id)//@PathVariable is used to get the parameters that we set on the  URL to get the data
	{
		
		User user = service.findOne(id);
		if(user==null)
			throw new UserNotFoundException("id: "+id); //For creation of user defined Exception
		
		EntityModel<User> entityModel=EntityModel.of(user);               //All the below steps are to create HATEOAS which is used to implement tree like structre
		WebMvcLinkBuilder link=linkTo(methodOn(this.getClass()).retriewAllUsers());
		entityModel.add(link.withRel("all-users"));
		return entityModel;
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) //@RequestBody is to get the added data from POST service in talendAPI application
	{ 
		User newUser=service.save(user);
		
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newUser.getId()).toUri();
		return ResponseEntity.created(location).build();
		}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id)//@PathVariable is used to get the parameters that we set on the  URL to get the data
	{
		
		 service.deleteByID(id);
		
	}
}
