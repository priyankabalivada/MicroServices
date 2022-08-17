package com.microservices.training.dao;





import java.util.Date;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)



   int id;
    
    String empId;
    String subject;
    Date assDate;
    String typ;



   
      public Employee(String empId, String subject, Date assDate, String typ) { this.empId
      = empId; this.subject = subject; this.assDate = assDate; this.typ = typ; }
     



   public Employee() {
        super();
    }



   
    public Employee(String empId, String subject, String typ) {
        super();
        this.empId = empId;
        this.subject = subject;
        this.typ = typ;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getEmpId() {
        return empId;
    }
    public void setEmpId(String empId) {
        this.empId = empId;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public Date getAssDate() {
        return assDate;
    }
    public void setAssDate(Date assDate) {
        this.assDate = assDate;
    }
    public String getTyp() {
        return typ;
    }
    public void setTyp(String typ) {
        this.typ = typ;
    }
    
    
}
