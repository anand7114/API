package com.example.demo.model;

import org.springframework.stereotype.Component;

@Component
public class Employee {
    private int id = 101;
    private String name = "John Doe";
    public int getId() { return id; }
    public String getName() { return name; }
    @Override public String toString() { return "Employee [ID=" + id + ", Name=" + name + "]"; }
}
