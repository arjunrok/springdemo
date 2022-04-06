package com.apptech.springdemo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.apptech.springdemo.model.Image;
import com.apptech.springdemo.repo.ImageRepository;

@Controller
public class UploadController {
	
	@Autowired
	private ImageRepository imgrepo;
	
	
	@GetMapping("/upload")
	public String upload() {
		
		return "UploadForm";
	}

	@PostMapping("/upload")
	public String saveImage(@RequestParam("photo") MultipartFile file, @ModelAttribute Image image, Model model ) {
		
		    if(file != null) {
		    	
		    	image.setName(file.getOriginalFilename());
                image.setType(file.getOriginalFilename().split("\\.")[1]);
                
                //save image name and type in db
                imgrepo.save(image);
                
                Path  impPath = Paths.get("src/main/resources/static/image/"+file.getOriginalFilename());
                //save image content in image folder
                try {
					Files.copy(file.getInputStream(), impPath,StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					e.printStackTrace();
				}
                
                model.addAttribute("message","upload success");
                return "UploadForm";
		    }
		
		    model.addAttribute("message","upload failed");
            return "UploadForm";	}
	
}