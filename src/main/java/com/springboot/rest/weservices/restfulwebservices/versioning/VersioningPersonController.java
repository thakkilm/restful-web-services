package com.springboot.rest.weservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

	@GetMapping("/v1/person")  // URI versioning used by companies like Twitter
	 public PersonV1 getFirstVersionOfPerson() {
		 return new PersonV1("Mahesh Chowdary");
	 }
	@GetMapping("/v2/person")
	 public PersonV2 getSecondVersionOfPerson() {
		 return new PersonV2(new Name("Mahesh", "Chowdary"));
	 }

	@GetMapping(path="/person",params = "version=1") // Rquest Parameter versioning used by companies like Amazon
	 public PersonV1 getFirstParamsVersionOfPerson() {
		 return new PersonV1("Mahesh Chowdary");
	 }
	@GetMapping(path="/person",params = "version=2")
	 public PersonV2 getSecondParamsVersionOfPerson() {
		 return new PersonV2(new Name("Mahesh", "Chowdary"));
	 }
	@GetMapping(path="/person/header",headers =   "X-API-VERSION=1")// Header versioning used by companies like Microsoft
	 public PersonV1 getFirstHeadersVersionOfPerson() {
		 return new PersonV1("Mahesh Chowdary");
	 }
	@GetMapping(path="/person/header",headers =   "X-API-VERSION=2")
	 public PersonV2 getSecondHeadersVersionOfPerson() {
		 return new PersonV2(new Name("Mahesh", "Chowdary"));
	 }
	
	@GetMapping(path="/person/accept", produces = "application/vnd.company.app-v1+json") // Accept type versioning used by companies like GitHub
	 public PersonV1 getFirstAcceptVersionOfPerson() {
		 return new PersonV1("Mahesh Chowdary");
	 }
	@GetMapping(path="/person/accept", produces = "application/vnd.company.app-v12+json")
	 public PersonV2 getSecondAcceptVersionOfPerson() {
		 return new PersonV2(new Name("Mahesh", "Chowdary"));
	 }

}


