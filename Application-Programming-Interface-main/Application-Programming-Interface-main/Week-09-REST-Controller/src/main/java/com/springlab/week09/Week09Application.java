package com.springlab.week09;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@SpringBootApplication
@RestController
@RequestMapping("/api/users")
public class Week09Application {
    private final List<User> users=new ArrayList<>(List.of(
        new User(1,"Sample User","Sample User@example.com"),
        new User(2,"Rahul","rahul@example.com")));

    public static void main(String[] args){
        SpringApplication.run(Week09Application.class,args);
    }

    @GetMapping public List<User> getUsers(){return users;}

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id){
        return users.stream().filter(u->u.id()==id).findFirst().orElseThrow();
    }

    @PostMapping
    public User add(@RequestBody User user){users.add(user);return user;}

    @PutMapping("/{id}")
    public User update(@PathVariable int id,@RequestBody User input){
        for(int i=0;i<users.size();i++){
            if(users.get(i).id()==id){
                User u=new User(id,input.name(),input.email());
                users.set(i,u); return u;
            }
        }
        throw new NoSuchElementException("User not found");
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id){
        if(!users.removeIf(u->u.id()==id)) throw new NoSuchElementException("User not found");
        return "Deleted user "+id;
    }

    record User(int id,String name,String email){}
}

