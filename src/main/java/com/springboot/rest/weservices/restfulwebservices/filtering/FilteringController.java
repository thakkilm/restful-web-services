package com.springboot.rest.weservices.restfulwebservices.filtering;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {
	
	@GetMapping("/filter")//This class is to show how to ignore any specif attributes from json API
	public SomeBean filter() {
		return new SomeBean("field1", "field2", "field3");
		
	}

}
