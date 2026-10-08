package com.springlab.week06;

import jakarta.persistence.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@SpringBootApplication
@RestController
@RequestMapping("/users")
public class Week06Application {
    private final UserRepository repo;

    public Week06Application(UserRepository repo) { this.repo=repo; }

    public static void main(String[] args) {
        SpringApplication.run(Week06Application.class,args);
    }

    @GetMapping
    public Page<User> users(
            @RequestParam(defaultValue="0") int page,
            @RequestParam(defaultValue="3") int size,
            @RequestParam(defaultValue="id") String sortBy,
            @RequestParam(defaultValue="asc") String direction) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        return repo.findAll(PageRequest.of(page,size,sort));
    }

    @GetMapping("/custom")
    public List<User> customSort() { return repo.findAllByOrderByAgeDesc(); }

    @PostMapping
    public User add(@RequestBody User u) { return repo.save(u); }

    @Entity
    static class User {
        @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
        Long id;
        String name;
        int age;
        public User(){}
        public User(String n,int a){name=n;age=a;}
        public Long getId(){return id;}
        public String getName(){return name;}
        public void setName(String n){name=n;}
        public int getAge(){return age;}
        public void setAge(int a){age=a;}
    }

    interface UserRepository extends JpaRepository<User,Long> {
        List<User> findAllByOrderByAgeDesc();
    }

    @Bean
    CommandLineRunner seed(UserRepository r) {
        return args -> {
            r.save(new User("Sample User",21));
            r.save(new User("Rahul",23));
            r.save(new User("Anu",20));
            r.save(new User("Priya",22));
            r.save(new User("Kiran",24));
        };
    }
}

