package com.apptech.springdemo.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.apptech.springdemo.repo.ImageRepository;

@Controller
public class GalleryController {
	
	@Autowired
	private ImageRepository imgRepo;
	
	@GetMapping("/gallery")
	public String gallery(Model model) {
		
//		  File  imgs = new File("src/main/resources/static/image");
//		  String[]   imageNames = imgs.list();
		  
		  // model.addAttribute("imglist",imageNames);
		
		//get image name from db
		 model.addAttribute("imglist",imgRepo.findAll());
		
		return "GalleryForm";
	}

}
