package com.springboot.rest.weservices.restfulwebservices.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND)//This sets manual response code that we want the system to be shown, else it will throw 500 error
public class UserNotFoundException extends RuntimeException {
	
	public UserNotFoundException(String message){
		super(message); 
		
	}

}
