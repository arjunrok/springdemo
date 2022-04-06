package com.apptech.springdemo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apptech.springdemo.model.Employee;

//@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
 
	
}
