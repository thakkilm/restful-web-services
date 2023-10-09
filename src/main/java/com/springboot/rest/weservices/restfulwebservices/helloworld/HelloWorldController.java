package com.springboot.rest.weservices.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	
	
	@GetMapping(path="/hello-world")
	public String helloworld() {
		return "Hello World";
	}
	@GetMapping(path="/hello-world-bean")
	public HelloworldBean helloworldBean() {
		return new HelloworldBean("Hello World");
	}
	@GetMapping(path="/hello-world/path-variable/{name}")
	public HelloworldBean helloworldPathVariable(@PathVariable String name) {
		return new HelloworldBean("Hello World: "+name);
	}
}
