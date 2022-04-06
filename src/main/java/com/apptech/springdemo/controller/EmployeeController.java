package com.apptech.springdemo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.apptech.springdemo.model.Employee;
import com.apptech.springdemo.service.IEmployeeService;

@Controller
public class EmployeeController {

	
	@Autowired
	private IEmployeeService service;

	@GetMapping("/employee")
	public String getEmployeeForm(HttpSession session) {

		if (session.getAttribute("validuser") == null) {

			return "LoginForm";
		}

		return "EmployeeForm";
	}

	@PostMapping("/employee")
	public String postEmployee(@ModelAttribute Employee employee, HttpSession session) {

		if (session.getAttribute("validuser") == null) {

			return "LoginForm";
		}

		service.addEmployee(employee);

		return "redirect:employee";
	}

	@GetMapping("/list")
	public String empList(Model model, HttpSession session) {

		if (session.getAttribute("validuser") == null) {

			return "LoginForm";
		}

		model.addAttribute("emplist", service.getAllEmployees());

		return "EmployeeData";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id, Model model, HttpSession session) {

		if (session.getAttribute("validuser") == null) {

			return "LoginForm";
		}

		service.deleteEmployee(id);

		return "redirect:/list";
	}

	@GetMapping("/edit")
	public String edit(@RequestParam("id") Long empid, Model model, HttpSession session) {

		if (session.getAttribute("validuser") == null) {

			return "LoginForm";
		}

		model.addAttribute("empObject", service.getEmployeeById(empid));

		return "EditEmployee";
	}

	@PostMapping("/update")
	public String updateEmp(@ModelAttribute Employee employee, HttpSession session) {

		if (session.getAttribute("validuser") == null) {

			return "LoginForm";
		}

		service.updateEmployee(employee);

		return "redirect:/list";
	}

}
