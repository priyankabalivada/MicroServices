package com.microservices.training.Service;


import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;



@Service
@EnableAutoConfiguration
public class LoginService {
    
    final String EMPLOYEE="emp";
    final String ADMIN="admin";
    
    public String checkEmplOrAdmin(String uname,String password,String uLevel) {
        Map<String,String> EmpMap=new HashMap<>();
        EmpMap.put("Arvind","12345");
        EmpMap.put("rashmi", "6789");
        Map<String,String> AdminMap=new HashMap<>();
        AdminMap.put("Arvind","12345");
        AdminMap.put("rashmi", "6789");
        if(EMPLOYEE.equalsIgnoreCase(uLevel)) {
            if(EmpMap.containsKey(uname)) {
                if(EmpMap.get(uname).equalsIgnoreCase(password)) {    
                    return "employee";    
                }
            }
        }
        else if(ADMIN.equalsIgnoreCase(uLevel)) {
            if(AdminMap.containsKey(uname)) {
                if(AdminMap.get(uname).equalsIgnoreCase(password)) {
                    return "admin";
                }
            }
        }
        return "loginFail";
    }}
