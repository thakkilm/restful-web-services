package com.springboot.rest.weservices.restfulwebservices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.rest.weservices.restfulwebservices.user.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
