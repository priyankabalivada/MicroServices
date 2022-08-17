package com.microservices.training.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.training.Service.EmployeeService;
import com.microservices.training.entity.Employee;

@RestController
public class EmployeeController {
    final String TECHNICAL="Technical";
    final String BEHAVIROL="Behavirol";
    @Autowired
    EmployeeService service;
    
    @GetMapping("/")
    public String getHome(){
        return "index";
    }
    
    /*
     * @GetMapping("/getTechnical") String getTechnicalAssementPage(){ return
     * "technical"; }
     *
     * @GetMapping("/getBehavirol") String getBehaviroulAssementPage(){ return
     * "behavirol"; }
     */
    
    
    /*
     * @PostMapping("/addTechnicalData") String
     * addTechnicalData(@ModelAttribute("uid") String uid,@ModelAttribute("subject")
     * String subject,@ModelAttribute("assDate") String assDate){ return common(uid,
     * subject,assDate, TECHNICAL); }
     *
     * @PostMapping("/addBehavirolData") String
     * addBehavirolData(@ModelAttribute("uid") String uid,@ModelAttribute("subject")
     * String subject,@ModelAttribute("assDate") String assDate){ return common(uid,
     * subject,assDate, BEHAVIROL); }
     */
    
    @PostMapping("/addEmployee")
    Employee addEmployee(@RequestBody Employee emp){
        return service.addEmpData(emp);
    }
    @PostMapping("/addBehavirolData")
    String addBehavirolData(@ModelAttribute("uid") String uid,@ModelAttribute("subject") String subject,@ModelAttribute("assDate") String assDate){
        return common(uid, subject,assDate, BEHAVIROL);
    }
    String common(@ModelAttribute("uid") String uid,@ModelAttribute("subject") String subject,@ModelAttribute("assDate") String assDate,String typ) {
        Date date1=null;
        try {
            date1 = new SimpleDateFormat("yyyy-mm-dd").parse(assDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Employee emp=service.addEmpData(uid, subject,date1, typ);
        if(emp!=null) {
            return "success";
        }
        else {
            return "fail";
        }
    }
    
    @GetMapping("/getEmployee")
    public List<Employee> getEmployee() {
        return service.getEmp();        
    }
    
    @PostMapping("/getEmployeeFault")
    Employee getEmployeeFault(@RequestBody Employee emp){
        int num = 1/0;
        return service.addEmpData(emp);
    }
}