package com.microservices.training.Service;

import java.util.Date;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;

import com.microservices.training.Repository.EmployeeRepository;
import com.microservices.training.entity.Employee;



@Service
@EnableAutoConfiguration
public class EmployeeService {
    
    @Autowired
    EmployeeRepository repository;
    
    public Employee addEmpData(Employee emp) {
        return repository.save(emp);
    }
    
      public Employee addEmpData(String uid,String subject,Date assDate,String typ) {
      Employee emp=new Employee(uid, subject, assDate, typ);
      return repository.save(emp);
       }
     
    
    public Employee addEmpData(String uid,String subject,String typ) {
        Employee emp=new Employee(uid, subject, typ);
        return repository.save(emp);
    }
    
    
    public List<Employee> getEmp() {    
        return repository.findAll();
    }
    
}