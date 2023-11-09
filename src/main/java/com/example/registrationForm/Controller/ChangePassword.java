package com.example.registrationForm.Controller;

import com.example.registrationForm.DTO.UserDTO;
import com.example.registrationForm.Entity.User;
import com.example.registrationForm.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/changepassword")
public class ChangePassword {
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public String changePassword(
            @RequestBody UserDTO userDTO) {

        // Validate the current password, new password, and confirmation
        if (!userDTO.getNewPassword().equals(userDTO.getConfirmPassword())){
            // Password and confirmation do not match, handle accordingly
            return "Password doesnot match";
        }

        // Retrieve the user from the database based on mobile number
        Optional<User> optionalUser = userRepository.findUserByMobileNumber(userDTO.getMobileNumber());

        User existingUser = optionalUser.orElse(null);

        if (existingUser != null) {
            // Check if the provided old password matches the stored password
            if (existingUser.getPassword().equals(userDTO.getOldPassword())){
                // Update the user's password with the new password
                existingUser.setPassword(userDTO.getNewPassword());
                userRepository.save(existingUser);
                return "Password Changed";
            } else {
                // Old password doesn't match, handle accordingly
                return "Old password doesn't match";
            }
        } else {
            // User not found for the given mobile number, handle accordingly
            return "User Not Found";
        }
    }
}

