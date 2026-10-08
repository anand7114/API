package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.service.EmployeeService;

@SpringBootTest
class EmployeeServiceTest {
    @Autowired private EmployeeService employeeService;
    @Test void testAutoWiringAndLogging() {
        assertThat(employeeService).isNotNull();
        assertThat(employeeService.getEmployeeDetails()).contains("Dependency Injection Successful");
    }
}
