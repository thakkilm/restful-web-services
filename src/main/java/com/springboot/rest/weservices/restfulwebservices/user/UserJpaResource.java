package com.springboot.rest.weservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

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

import com.springboot.rest.weservices.restfulwebservices.jpa.PostRepository;
import com.springboot.rest.weservices.restfulwebservices.jpa.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserJpaResource {

	
	private UserRepository userRepository;
	private PostRepository postRepository;

	public UserJpaResource(UserRepository userRepository,PostRepository postRepository) {
		super();
		
		this.userRepository=userRepository;
		this.postRepository=postRepository;
	}

	@GetMapping("/jpa/users")
	public List<User> retriewAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> retriewUser(@PathVariable int id)// @PathVariable is used to get the parameters that we set
																// on the URL to get the data
	{

		Optional<User> user = userRepository.findById(id);
		if (user.isEmpty())
			throw new UserNotFoundException("id: " + id); // For creation of user defined Exception

		EntityModel<User> entityModel = EntityModel.of(user.get()); // All the below steps are to create HATEOAS which is used
																// to implement tree like structre
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retriewAllUsers());
		entityModel.add(link.withRel("all-users"));
		return entityModel;
	}

	@PostMapping("/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) // @RequestBody is to get the added data from
																			// POST service in talendAPI application
	{
		User newUser = userRepository.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id)// @PathVariable is used to get the parameters that we set on the URL to
												// get the data
	{
		userRepository.deleteById(id);

	}
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrievPostsForUser(@PathVariable int id)// @PathVariable is used to get the parameters that we set on the URL to
												// get the data
	{
		Optional<User> user=userRepository.findById(id);
		return user.get().getPosts();

	}
	
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPostForUser(@PathVariable int id,@Valid @RequestBody Post post )// @PathVariable is used to get the parameters that we set															// on the URL to get the data
	{
		Optional<User> user = userRepository.findById(id);
		if (user.isEmpty())
			throw new UserNotFoundException("id: " + id); // For creation of user defined Exception
		post.setUser(user.get());
		Post savedPost=postRepository.save(post);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	@GetMapping("/jpa/users/{id}/posts/{id2}")
	public Post retrievSpecificPostsOfAUser(@PathVariable int id,@PathVariable int id2)// @PathVariable is used to get the parameters that we set on the URL to
												// get the data
	{
		Optional<User> user=userRepository.findById(id);
		List<Post> posts = user.get().getPosts();
		Predicate<? super Post> predicate = pots -> pots.getId().equals(id2); 
		return posts.stream().filter(predicate).findFirst().orElse(null);
		

	}
	
	
}
