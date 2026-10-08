package com.springlab.week05;

import jakarta.persistence.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@SpringBootApplication
@RestController
@RequestMapping("/users")
public class Week05Application {
    private final UserRepository repo;

    public Week05Application(UserRepository repo) { this.repo = repo; }

    public static void main(String[] args) {
        SpringApplication.run(Week05Application.class, args);
    }

    @GetMapping
    public List<User> all() { return repo.findAll(); }

    @GetMapping("/{id}")
    public User one(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }

    @PostMapping
    public User create(@RequestBody User user) { return repo.save(user); }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User input) {
        User u = repo.findById(id).orElseThrow();
        u.setName(input.getName());
        u.setEmail(input.getEmail());
        return repo.save(u);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        repo.deleteById(id);
        return "User deleted";
    }

    @Entity
    static class User {
        @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
        private Long id;
        private String name;
        private String email;

        public User() {}
        public User(String name, String email) { this.name=name; this.email=email; }
        public Long getId(){return id;}
        public String getName(){return name;}
        public void setName(String n){name=n;}
        public String getEmail(){return email;}
        public void setEmail(String e){email=e;}
    }

    interface UserRepository extends JpaRepository<User,Long> {}
}

