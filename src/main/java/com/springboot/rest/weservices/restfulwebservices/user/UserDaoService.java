package com.springboot.rest.weservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class UserDaoService {
	
	private static List<User> users=new ArrayList<>();
	private static int userCount=0;
	static {
		users.add(new User(++userCount,"Mahesh",LocalDate.now().minusYears(28)));
		users.add(new User(++userCount,"Lokesh",LocalDate.now().minusYears(30)));
		users.add(new User(++userCount,"Chaitu",LocalDate.now().minusYears(29)));
	}
	public List<User> findAll(){
		return users;
	}
	public User findOne(int id) {
		Predicate<? super User> predicate = user -> user.getId().equals(id); 
		return users.stream().filter(predicate).findFirst().get();
	}
	public User save(User user) {
		user.setId(++userCount);
		users.add(user);
		return user;
	}
}
