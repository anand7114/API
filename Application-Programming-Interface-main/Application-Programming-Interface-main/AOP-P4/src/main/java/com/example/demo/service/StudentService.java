package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Student;

@Service
public class StudentService {
    @Autowired private Student student;
    public String displayStudentDetails() { return student.getName() + " with Roll Series: " + student.getRollNo(); }
    public void updateStudentName(String newName) { student.setName(newName); }
}
