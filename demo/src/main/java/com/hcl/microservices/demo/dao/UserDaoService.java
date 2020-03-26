package com.hcl.microservices.demo.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.hcl.microservices.demo.bean.User;

@Component
public class UserDaoService {

	private static List<User> userList = new ArrayList<User>();
	private static int userCount = 7;
	
	static {
		userList.add(new User(1,"umesh",new Date()));
		userList.add(new User(2,"Geeta",new Date()));
		userList.add(new User(3,"Ravi",new Date()));
		userList.add(new User(4,"Rekha",new Date()));
		userList.add(new User(5,"Deeksha",new Date()));
		userList.add(new User(6,"Sharma",new Date()));
		userList.add(new User(7,"kapil",new Date()));
		}	
	
	public List<User> findAll() {
		return userList;
	}
	
	public User saveUser(User user) {
		if(user.getId()==null) {
			user.setId(++userCount);
		}
		userList.add(user);
		return user;
	}
	
	public User findUserById(Integer userId) {
		for(User user:userList) {
			if(user.getId()==userId) {
				return user;
			}
		}
		return null;
		}
	}
