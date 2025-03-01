package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.dto.LoginDTO;
import com.examly.springapp.dto.UserDTO;
import com.examly.springapp.exceptions.UserAlreadyExistsException;
import com.examly.springapp.exceptions.UserNotFoundException;
import com.examly.springapp.model.User;
import com.examly.springapp.service.UserService;

import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController // Marks this class as a RESTful controller
@Tag(name = "User", description = "Endpoints for user authentication and management.") // Swagger tag to describe this controller
@CrossOrigin // Allows cross-origin requests
@RequestMapping("/api") // Base URL for all the endpoints in this controller
public class AuthController {

    private final UserService userService;

    // @Autowired // Automatically injects an instance of UserService
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register") // Endpoint to register a new user
    public ResponseEntity<User> registerUser(@Valid @RequestBody User user) throws UserAlreadyExistsException {
        user = userService.registerUser(user);
        return ResponseEntity.status(201).body(user);
    }


    @PostMapping("/login") // Endpoint to log in an existing user
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginDTO user) throws UserNotFoundException {
        UserDTO userDTO = userService.loginUser(user);
        if (userDTO != null) {
            return ResponseEntity.status(200).body(userDTO);
        }
        return ResponseEntity.status(500).body("Login Failed!");
    }
}

