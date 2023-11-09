package com.example.registrationForm.DTO;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Integer id;
    private String userName;
    private String password;
    private Long mobileNumber;
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
}
