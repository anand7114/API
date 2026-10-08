package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PortalController {
    @GetMapping("/")
    public String welcome() { return "Welcome to Aditya University's Portal!"; }

    @GetMapping("/greet")
    public String greet(@RequestParam(value = "name", defaultValue = "Student") String name) {
        return "Greetings, " + name + "! Welcome to your Spring Web MVC Dashboard.";
    }
}
