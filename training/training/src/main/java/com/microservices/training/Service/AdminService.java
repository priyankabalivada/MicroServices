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
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.microservices.training.dao.Employee;


@Service
@EnableAutoConfiguration
public class AdminService {
    
     @Autowired
     RestTemplate restTemplate;
     
     @Autowired
       private LoadBalancerClient loadBalancerClient;
     
    
     @Autowired
     private CircuitBreakerFactory circuitBreakerFactory;
     
     @Value("${pivotal.employeeservice.name}")
      protected String employeeService;
    
    public ModelAndView getAllRegistraion()
    {
        ModelAndView mv=new ModelAndView();
        //final String uri = "http://localhost:8096/getEmployee";
        //List<Employee> result = restTemplate.getForObject(uri, List.class);
        ServiceInstance instance=loadBalancerClient.choose(employeeService);
        
        URI uri=URI.create(String.format("http://%s:%s",
                instance.getHost(),instance.getPort()));
        
        String url=uri.toString()+"/getEmployee";
         
           
            CircuitBreaker circuitBreaker = circuitBreakerFactory.create("GetReviewCircuitBreaker");
            List<Employee> empList=circuitBreaker.run(() -> restTemplate.getForObject(url, List.class), throwable -> getDefaultEmployeeList());
        
        System.out.println("details of results:"+empList.toString());
        mv.addObject("emp",empList);
        mv.setViewName("employeeDetails");
        return mv;
        
    }
    
      private List<Employee> getDefaultEmployeeList() {
          List<Employee> list=new ArrayList<>();
          list.add(new Employee("1","Default",new Date(01-01-2022),"Default"));
            return list;
        }
      
      public ModelAndView getEmpFault() {    
            //final String url = "http://localhost:8096/getEmployee";
          ModelAndView mv=new ModelAndView();
            //final String uri = "http://localhost:8096/getEmployee";
            //List<Employee> result = restTemplate.getForObject(uri, List.class);
            ServiceInstance instance=loadBalancerClient.choose(employeeService);
            
            URI uri=URI.create(String.format("http://%s:%s",
                    instance.getHost(),instance.getPort()));
            
            String url=uri.toString()+"/getEmployeeFault";
             
               
                CircuitBreaker circuitBreaker = circuitBreakerFactory.create("GetReviewCircuitBreaker");
                List<Employee> empList=circuitBreaker.run(() -> restTemplate.getForObject(url, List.class), throwable -> getDefaultEmployeeList());
            
            System.out.println("details of results:"+empList.toString());
            mv.addObject("emp",empList);
            mv.setViewName("employeeDetails");
            return mv;
      }}