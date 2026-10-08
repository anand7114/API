package com.example.demo52;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.demo52.model.User;
import com.example.demo52.repository.UserRepository;

@SpringBootTest
public class CrudUserTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void executeDatabaseCrudSequence() {

        System.out.println(
            "\n========== SPRING DATA JPA CRUD TEST =========="
        );

        // ==========================================
        // 1. CREATE
        // ==========================================

        User user1 =
            new User("John Doe", "john.doe@aditya.edu.in");

        User user2 =
            new User("Alice Smith", "alice.s@aditya.edu.in");

        userRepository.save(user1);
        userRepository.save(user2);

        System.out.println("\n[CREATE]");
        System.out.println("Users created successfully.");

        // ==========================================
        // 2. READ
        // ==========================================

        List<User> users = userRepository.findAll();

        System.out.println("\n[READ ALL]");
        users.forEach(System.out::println);

        // ==========================================
        // 3. UPDATE
        // ==========================================

        User existingUser =
            userRepository.findById(user1.getId())
                           .orElseThrow();

        existingUser.setName("John Developer");

        userRepository.save(existingUser);

        System.out.println("\n[UPDATE]");
        System.out.println(
            "Updated User: " + existingUser
        );

        // ==========================================
        // 4. DELETE
        // ==========================================

        userRepository.deleteById(user2.getId());

        System.out.println("\n[DELETE]");
        System.out.println(
            "User deleted successfully."
        );

        // ==========================================
        // 5. COUNT
        // ==========================================

        long count = userRepository.count();

        System.out.println("\n[COUNT]");
        System.out.println(
            "Remaining users: " + count
        );

        System.out.println(
            "\n========== TEST COMPLETED ==========\n"
        );
    }
}
