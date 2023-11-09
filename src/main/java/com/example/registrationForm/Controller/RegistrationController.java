package com.example.registrationForm.Controller;

import com.example.registrationForm.Entity.User;
import com.example.registrationForm.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/register")
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        try {
        // Validate input
        if (user.getUserName() == null || user.getPassword() == null) {
            return ResponseEntity.badRequest().body("Username,password are required.");
        }

        // Check if the username is already taken
        Optional<User> data= userRepository.findUserByMobileNumber(user.getMobileNumber());
        if (data != null && !data.isEmpty() ) {
            return ResponseEntity.badRequest().body("User name already exists.");
        }

        // Registration logic

            userRepository.save(user);
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration failed");
        }
    }
}

