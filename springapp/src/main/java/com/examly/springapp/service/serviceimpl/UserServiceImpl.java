package com.examly.springapp.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.examly.springapp.config.JwtUtils;
import com.examly.springapp.config.MyUserDetailsService;
import com.examly.springapp.exceptions.UserAlreadyExistsException;
import com.examly.springapp.exceptions.UserNotFoundException;
import com.examly.springapp.dto.LoginDTO;
import com.examly.springapp.dto.UserDTO;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.UserRepo;
import com.examly.springapp.service.UserService;

// Used with classes that handle business logic
@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final JwtUtils jwtUtils;
    private final MyUserDetailsService myUserDetailsService;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, JwtUtils jwtUtils, MyUserDetailsService myUserDetailsService) {
        this.userRepo = userRepo;
        this.jwtUtils = jwtUtils;
        this.myUserDetailsService = myUserDetailsService;
    }

    @Override
    public User registerUser(User user) throws UserAlreadyExistsException {
        User existingUser = userRepo.findByEmail(user.getEmail());
        if (existingUser != null) {
            throw new UserAlreadyExistsException("User already exits");
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user = userRepo.save(user);
        return user;
    }

    @Override
    public UserDTO loginUser(LoginDTO user) throws UserNotFoundException{
        UserDetails userDetails ;
        try{
            userDetails = myUserDetailsService.loadUserByUsername(user.getEmail());
        }catch(UsernameNotFoundException e){
            return null;
        }
        User existingUser = userRepo.findByEmail(user.getEmail());
        if(userDetails == null){
            throw new UserNotFoundException("Email not registered");
        }
        if (new BCryptPasswordEncoder().matches(user.getPassword(), userDetails.getPassword())) {
            String token = jwtUtils.createToken(existingUser);
            UserDTO userDTO = new UserDTO();
            userDTO.setToken(token);
            return userDTO;
        }
        return null;
    }

}
