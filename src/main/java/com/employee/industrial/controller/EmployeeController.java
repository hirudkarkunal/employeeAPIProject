package com.employee.industrial.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.industrial.entity.Employee;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	HashMap<Integer, Employee> cache = new HashMap<>();

	@PostMapping("/createEmployee")
	public Employee createEmployee(@RequestBody Employee employee) {

		cache.put(employee.empId, employee);

		return cache.get(employee.empId);
	}

	@GetMapping("/getemployee/{empId}")
	public Employee getemployee(@RequestParam Integer empId) {

		return cache.get(empId);
	}

	@PutMapping("/updateEmployee/{empId}")
	public Employee updateEmployee(@RequestBody Employee employee) {
		Employee oldEmp = cache.get(employee.empId);
		oldEmp.setEmpName(employee.getEmpName());
		oldEmp.setAddress(employee.getAddress());
		oldEmp.setSalary(employee.getSalary());

		cache.put(oldEmp.getEmpId(), oldEmp);
		return cache.get(employee.getEmpId());
	}

	@DeleteMapping("/deleteEmployee/{empId)")
	public Employee deleteEmployee(@RequestParam Integer empId) {

		return cache.remove(empId);
	}

	// getAllEmployee -->
	@GetMapping("/getAllEmp")
	public List<Employee> getAllEmp(@RequestBody Employee employee) {
		List<Employee> emplist = new ArrayList<>();

		return emplist = cache.values().stream().collect(Collectors.toList());

	}

	// getAllEmp50Kclub -->
	@GetMapping("/getAllEmp50kClub")
	public List<Employee> getAllEmp50kClub(@RequestBody Employee employee) {
		List<Employee> emplist = new ArrayList<>();

		List<Employee> emplist50k = emplist.stream().filter(integer -> integer.getSalary() > 50000)
				.collect(Collectors.toList());
		return null;
	}
}
