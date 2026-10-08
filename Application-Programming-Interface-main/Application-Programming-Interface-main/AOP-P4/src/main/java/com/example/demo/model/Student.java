package com.example.demo.model;

import org.springframework.stereotype.Component;

@Component
public class Student {
    private String name = "Charan";
    private String rollNo = "24B11AI168";
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getRollNo() { return rollNo; }
    public void setRollNo(String rollNo) { this.rollNo = rollNo; }
}

