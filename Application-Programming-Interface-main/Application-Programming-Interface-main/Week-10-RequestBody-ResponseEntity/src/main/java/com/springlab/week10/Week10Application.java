package com.springlab.week10;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@SpringBootApplication
@RestController
@RequestMapping("/api/users")
public class Week10Application {
    private final Map<Long,User> users=new HashMap<>();
    private long nextId=1;

    public static void main(String[] args){
        SpringApplication.run(Week10Application.class,args);
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User input){
        User u=new User(nextId++,input.name(),input.email());
        users.put(u.id(),u);
        return ResponseEntity.status(HttpStatus.CREATED).body(u);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable long id){
        User u=users.get(id);
        return u==null ? ResponseEntity.notFound().build()
                       : ResponseEntity.ok(u);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable long id,@RequestBody User input){
        if(!users.containsKey(id)) return ResponseEntity.notFound().build();
        User u=new User(id,input.name(),input.email());
        users.put(id,u);
        return ResponseEntity.ok(u);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id){
        if(!users.containsKey(id))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        users.remove(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    @GetMapping
    public ResponseEntity<Collection<User>> all(){
        return ResponseEntity.ok(users.values());
    }

    record User(long id,String name,String email){
        User(String name,String email){this(0,name,email);}
    }
}

