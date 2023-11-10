package com.example.registrationForm.Controller;

import com.example.registrationForm.DTO.UserDTO;
import com.example.registrationForm.Entity.User;
import com.example.registrationForm.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getUser/{mobileNumber}")
    public ResponseEntity<?> getUserByMobileNumber(@RequestParam Long mobileNumber) {
        try {
            // Retrieve user by mobile number
            Optional<User> userData = userRepository.findUserByMobileNumber(mobileNumber);

            // Check if the user exists
            if (userData.isPresent()) {
                return ResponseEntity.ok(userData.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error retrieving user");
        }
    }

}

