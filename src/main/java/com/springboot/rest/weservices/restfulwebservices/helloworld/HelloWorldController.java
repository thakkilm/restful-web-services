package com.springboot.rest.weservices.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	private MessageSource messageSource; //This is for internationalization (for different lanuages)
	
	
	public HelloWorldController(MessageSource messageSource) {
		
		this.messageSource = messageSource;
	}
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
	
	@GetMapping(path="/hello-world-internationalized")
	public String helloworldInternationalized() {
		Locale locale=LocaleContextHolder.getLocale();
		return messageSource.getMessage("good.morning.message", null, "Default Message", locale);
		
	}
}
