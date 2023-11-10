package com.example.registrationForm.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "user_sequence", sequenceName = "users_id_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private Integer id;
    @Column(name = "user_name")

    private String userName;
    @Column(name = "password")

    private String password;
    @Column(name = "mobile_number")

    private Long mobileNumber;


    public User(Integer id, String userName, String password) {
    }
}
