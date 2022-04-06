package com.apptech.springdemo.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.apptech.springdemo.model.User;
import com.apptech.springdemo.repo.UserRepository;

@Controller
public class UserController {
	
	 private static final  Logger  log = LoggerFactory.getLogger(UserController.class);
	

	@Autowired
	private UserRepository userRepo;

	// =================== login section =======================
	@GetMapping("/login")
	public String getLogin() {
		
		log.info("===== inside login form ==========");

		return "LoginForm";
	}

	@PostMapping("/login")
	public String doLogin(@ModelAttribute User user, Model model, HttpSession session) {

		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		User usr = userRepo.findByUsernameAndPassword(user.getUsername(), user.getPassword());

		if (usr != null) {
			
			 log.info("user login success");

			session.setAttribute("validuser", usr);
			session.setMaxInactiveInterval(120);

			//model.addAttribute("un", user.getUsername());

			return "Home";
		}

		log.info(" user login failed");
		model.addAttribute("message", "user not found");

		return "LoginForm";
	}

	// ================ signup section ==================

	// 1. open form

	@GetMapping("/signup")
	public String getSignup() {

		return "SignupForm";
	}

	// 2. get submitted data from signup form

	@PostMapping("/signup")
	public String saveUser(@ModelAttribute User user) {

		// send data to db(user table)

		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		userRepo.save(user);

		return "LoginForm";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		log.warn(" logout success");
		session.invalidate();
		
		return "LoginForm";
	}
	
	@GetMapping("/profile")
	public String getProfile() {
		log.error("profile data");
		return "Profile";
	}

}
