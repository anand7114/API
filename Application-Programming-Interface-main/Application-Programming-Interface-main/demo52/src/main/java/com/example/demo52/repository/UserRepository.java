package com.example.demo52.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo52.model.User;

public interface UserRepository
        extends JpaRepository<User, Long> {
}