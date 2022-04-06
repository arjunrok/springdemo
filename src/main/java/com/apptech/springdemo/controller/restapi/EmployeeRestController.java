package com.apptech.springdemo.controller.restapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apptech.springdemo.model.Employee;
import com.apptech.springdemo.service.IEmployeeService;

@RestController
public class EmployeeRestController {
	
	@Autowired
	private IEmployeeService  service;
	
	   @GetMapping("/emp/api/list")
	  public List<Employee> getAllEmployees() {
		  
		  return service.getAllEmployees();
	  }
	   
	   @PostMapping("/emp/api/add")
	   public String addEmp(@RequestBody Employee  employee) {
		   
		   service.addEmployee(employee);
		   return "added success";
	   }
	   
	   @PutMapping("/emp/api/update")
	   public String update(@RequestBody Employee  employee) {
		   
		   service.updateEmployee(employee);
		   return "update success";
	   }

	   @DeleteMapping("/emp/api/delete/{id}")
	   public String detete(@PathVariable Long id) {
		   
		   service.deleteEmployee(id);
		   return "delete success";
	   }
}
