package com.springboot.rest.weservices.restfulwebservices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.rest.weservices.restfulwebservices.user.Post;
import com.springboot.rest.weservices.restfulwebservices.user.User;

public interface PostRepository extends JpaRepository<Post, Integer>{

}
