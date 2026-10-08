package com.example.demo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

@SpringBootApplication
public class SpringJdbcApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringJdbcApplication.class, args);
    }

    @Bean
    @SuppressWarnings("unused")
    CommandLineRunner demo(ProductRepository repository) {
        return args -> {
            repository.createTable();
            repository.save(new Product(101L, "MacBook Pro", 129999.00));
            repository.save(new Product(102L, "Keyboard", 4500.00));
            repository.save(new Product(103L, "Wireless Mouse", 1800.00));
            List<Product> products = repository.findAll();
            products.forEach(product -> System.out.println(product));
        };
    }
}
