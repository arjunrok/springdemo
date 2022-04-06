package com.apptech.springdemo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import com.apptech.springdemo.model.Employee;
import com.apptech.springdemo.repo.EmployeeRepository;
import com.apptech.springdemo.service.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private EmployeeRepository empRepo;

	@Override
	public void addEmployee(Employee employee) {
		empRepo.save(employee);
	}

	@Override
	public void deleteEmployee(Long id) {

		empRepo.deleteById(id);
	}

	@Override
	public void updateEmployee(Employee employee) {

		empRepo.save(employee);
	}

	@Override
	public Employee getEmployeeById(Long id) {
		return empRepo.getById(id);
	}

	@Override
	public List<Employee> getAllEmployees() {

		return empRepo.findAll();
	}
}
