package com.apptech.springdemo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apptech.springdemo.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	 User findByUsernameAndPassword(String un, String psw);
}
