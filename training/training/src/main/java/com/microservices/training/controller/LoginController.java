package com.microservices.training.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.microservices.training.Service.AdminService;
import com.microservices.training.Service.LoginService;
import com.microservices.training.dao.Employee;
import com.microservices.training.Service.EmployeeService;
@Controller
@EnableAutoConfiguration
public class LoginController{

final String TECHNICAL="Technical";
final String BEHAVIROL="Behavirol";

@Autowired
LoginService loginservice;

@Autowired
AdminService adminService;

@Autowired
EmployeeService empService;

@GetMapping("/")
String login(){
    return "index";
}

@PostMapping("/checkAdminOrEmployee")
public String checkAdminOrEmployee(@ModelAttribute("uName") String uname,@ModelAttribute("password")String password,@ModelAttribute("uLevel")String uLevel) {
    String str=loginservice.checkEmplOrAdmin(uname, password, uLevel);
    return str;
}

@GetMapping("/getAllRegistraion")
public ModelAndView getAllRegistraion()
{
    return adminService.getAllRegistraion();    
}

@GetMapping("/getTechnical")
String getTechnicalAssementPage(){
    return "technical";
}
@GetMapping("/getBehavirol")
String getBehaviroulAssementPage(){
    return "behavirol";
}



@PostMapping("/addTechnicalData")
String addTechnicalData(@ModelAttribute("uid") String uid,@ModelAttribute("subject") String subject,@ModelAttribute("assDate") String assDate){
    return common(uid, subject,assDate, TECHNICAL);
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
    Employee emp=empService.addEmpData(uid, subject,date1, typ);
    if(emp!=null) {
        return "success";
    }
    else {
        return "fail";
    }
}


/*
 * @GetMapping("/getEmployee") List<Employee> getEmployee(){ return
 * empService.getEmp(); }
 */
  
  @GetMapping("/getEmployeeFault")
  public ModelAndView getFault(){
      return  adminService.getEmpFault();
      }}