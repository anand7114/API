package com.springlab.week07;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@SpringBootApplication
@RestController
@RequestMapping("/users")
public class Week07Application {
    private final Map<Long,User> users=new LinkedHashMap<>();
    private final AtomicLong ids=new AtomicLong();

    public static void main(String[] args) {
        SpringApplication.run(Week07Application.class,args);
    }

    @GetMapping public Collection<User> getAll(){return users.values();}

    @GetMapping("/{id}")
    public User getOne(@PathVariable long id){
        User u=users.get(id);
        if(u==null) throw new NoSuchElementException("User not found");
        return u;
    }

    @PostMapping
    public User create(@RequestBody User input){
        long id=ids.incrementAndGet();
        User u=new User(id,input.name(),input.email());
        users.put(id,u);
        return u;
    }

    @PutMapping("/{id}")
    public User update(@PathVariable long id,@RequestBody User input){
        if(!users.containsKey(id)) throw new NoSuchElementException("User not found");
        User u=new User(id,input.name(),input.email());
        users.put(id,u);
        return u;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id){
        if(users.remove(id)==null) throw new NoSuchElementException("User not found");
        return "User deleted successfully";
    }

    record User(long id,String name,String email){
        User(String name,String email){this(0,name,email);}
    }
}

