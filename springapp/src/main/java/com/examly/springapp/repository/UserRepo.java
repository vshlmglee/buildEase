package com.examly.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.User;

// Handles storing and fetching data from a database.
@Repository
public interface UserRepo extends JpaRepository<User,Long>{
    User findByUsername(String username);
    
    User findByEmail(String email);
}