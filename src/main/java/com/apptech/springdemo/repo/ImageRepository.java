package com.apptech.springdemo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apptech.springdemo.model.Image;

public interface ImageRepository extends JpaRepository<Image, Integer>{

	  
}
