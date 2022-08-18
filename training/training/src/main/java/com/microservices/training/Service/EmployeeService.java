package com.microservices.training.Service;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.training.dao.Employee;

@Service
@EnableAutoConfiguration
public class EmployeeService {
    
    
      @Autowired
      RestTemplate restTemplate;
      
      @Autowired
        private LoadBalancerClient loadBalancerClient;
      
    
      @Autowired
      private CircuitBreakerFactory circuitBreakerFactory;
     
      
      @Value("${pivotal.employeeservice.name}")
      protected String employeeService;
      
      public Employee addEmpData(String uid,String subject,Date assDate,String typ) {
          Employee emp=new Employee(uid, subject, assDate, typ);
         
                //final String url = "http://localhost:8096/addEmployee";
                ServiceInstance instance=loadBalancerClient.choose(employeeService);
                
                URI uri=URI.create(String.format("http://%s:%s",
                        instance.getHost(),instance.getPort()));
                
                String url=uri.toString()+"/addEmployee";
                 HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);
                    ObjectMapper Obj = new ObjectMapper();  
                     
                    // Converting the Java object into a JSON string  
                    String jsonStr=null;
                    try {
                        jsonStr = Obj.writeValueAsString(emp);
                    } catch (JsonProcessingException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                        
                    HttpEntity<String> request = new HttpEntity<String>(jsonStr, headers);
                   
                   
                    CircuitBreaker circuitBreaker = circuitBreakerFactory.create("GetReviewCircuitBreaker");
                    Employee resultEmp=circuitBreaker.run(() -> restTemplate.postForObject(url, request, Employee.class), throwable -> getDefaultEmployee());
               // Employee resultEmp = restTemplate.postForObject(url, request, Employee.class);
                System.out.println("details of results:"+resultEmp.toString());
                                
          return resultEmp;
      }
     
      private Employee getDefaultEmployee() {
            return new Employee("1","Default",new Date(01-01-2022),"Default");
        }
     
    
    
}

