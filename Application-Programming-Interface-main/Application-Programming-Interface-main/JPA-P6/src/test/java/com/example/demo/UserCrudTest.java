package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@SpringBootTest
class UserCrudTest {
    @Autowired private UserRepository userRepository;
    @Test void executeDatabaseCrudSequence() {
        User first = userRepository.save(new User("John Doe", "john.doe@aditya.edu.in"));
        User second = userRepository.save(new User("Alice Smith", "alice.s@aditya.edu.in"));
        assertThat(userRepository.findAll()).hasSize(2);
        first.setName("John Developer");
        userRepository.save(first);
        assertThat(userRepository.findById(first.getId()).orElseThrow().getName()).isEqualTo("John Developer");
        userRepository.deleteById(second.getId());
        assertThat(userRepository.count()).isEqualTo(1);
    }
}
