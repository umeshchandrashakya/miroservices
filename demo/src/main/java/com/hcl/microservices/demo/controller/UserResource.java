package com.hcl.microservices.demo.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hcl.microservices.demo.bean.User;

import com.hcl.microservices.demo.dao.UserDaoService;
import com.hcl.microservices.demo.exceptions.UserNotFoundException;

@RestController
public class UserResource {

	@Autowired
	UserDaoService userService;
	
	//retrieve user
	@GetMapping(path="/users")
	public List<User> findAllUser(){
		return userService.findAll();
	}
	
	@GetMapping(path="/user/{id}")
	public User retriveUser(@PathVariable Integer id) {
		User user = userService.findUserById(id);
		if(user==null) 
			throw new UserNotFoundException("id-"+id);
		return user;
	}
	
	@PostMapping("/user")
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		 
		User savedUser = userService.saveUser(user);
		URI location =ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
		}
	
}
