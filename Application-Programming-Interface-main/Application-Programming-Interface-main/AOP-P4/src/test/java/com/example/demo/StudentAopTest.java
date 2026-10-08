package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.demo.service.StudentService;

@SpringBootTest
class StudentAopTest {
    @Autowired private StudentService studentService;
    @Test void testAopAdviceInterception() {
        assertThat(studentService.displayStudentDetails()).contains("John Doe");
        studentService.updateStudentName("Alice Smith");
        assertThat(studentService.displayStudentDetails()).contains("Alice Smith");
    }
}
