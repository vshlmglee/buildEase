package com.examly.springapp.service;

import com.examly.springapp.dto.LoginDTO;
import com.examly.springapp.dto.UserDTO;
import com.examly.springapp.exceptions.UserAlreadyExistsException;
import com.examly.springapp.exceptions.UserNotFoundException;
import com.examly.springapp.model.User;

    
public interface UserService{

    // Method to log in a user
    UserDTO loginUser(LoginDTO user) throws UserNotFoundException;

    
    // Method to register a new user
    User registerUser(User user) throws UserAlreadyExistsException;

}

