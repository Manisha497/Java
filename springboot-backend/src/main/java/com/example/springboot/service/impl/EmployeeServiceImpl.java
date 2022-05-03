package com.example.springboot.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.model.Employee;
import com.example.springboot.repository.EmployeeRepository;
import com.example.springboot.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeRepository employeeRepo;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepo) {
		super();
		this.employeeRepo = employeeRepo;
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return employeeRepo.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return employeeRepo.findAll();
	}

	@Override
	public Employee getEmployeeByID(long id) {
		return employeeRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
		// TODO Auto-generated method stub
//		Optional<Employee> employee = employeeRepo.findById(id);
//		if(employee.isPresent()) {
//			return employee.get();
//		}
//		else {
//			throw new ResourceNotFoundException("Employee", "id", id);
//		}
	}

	@Override
	public Employee updateEmployee(Employee employee, long id) {
		// TODO Auto-generated method stub
		//check if mentioned employee id exists in db
		Employee existingEmployee = employeeRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee","id",id));
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());
		
		//save existing employee in DB
		employeeRepo.save(existingEmployee);
		return existingEmployee;
	}

	@Override
	public void deleteEmployee(long id) {
		//check whether employee exists
		employeeRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee","id",id));
		employeeRepo.deleteById(id);
	}

}
