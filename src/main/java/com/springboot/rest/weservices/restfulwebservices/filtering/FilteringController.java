package com.springboot.rest.weservices.restfulwebservices.filtering;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
	
	@GetMapping("/static-filter")//This class is to show how to ignore any specif attributes from json API
	public SomeBean filter() {
		return new SomeBean("field1", "field2", "field3");
		
	}
	
	@GetMapping("/dynamic-filter")//This class is to show how to ignore any specif attributes from json API dynamically
	public MappingJacksonValue dynamicFilter() {
		SomeBean someBean=new SomeBean("field1", "field2", "field3");
		MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(someBean);
		SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");
		FilterProvider filters=new SimpleFilterProvider().addFilter("SomeBean", filter);//The attribute inside AddFilter should match the attribute in Somebean class as well
		mappingJacksonValue.setFilters(filters);
		return mappingJacksonValue;
		
	}


}
