package com.example.registrationForm.Repository;

import com.example.registrationForm.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByMobileNumber(Long mobile);
}
