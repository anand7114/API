package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;

@Service
public class EmployeeService {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    @Autowired private Employee employee;
    public String getEmployeeDetails() {
        logger.info("Fetching employee information via EmployeeService...");
        logger.debug("Employee instance details: {}", employee);
        return "Dependency Injection Successful: " + employee;
    }
}
