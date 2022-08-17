package com.microservices.training.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.training.entity.Employee;

public abstract class EmployeeRepository implements  JpaRepository<Employee,Integer>{

	
}
