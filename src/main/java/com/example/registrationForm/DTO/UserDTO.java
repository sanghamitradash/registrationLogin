package com.example.registrationForm.DTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "user_sequence", sequenceName = "users_id_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private Integer id;
    private String userName;
    private String password;
    private Long mobileNumber;
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
}
