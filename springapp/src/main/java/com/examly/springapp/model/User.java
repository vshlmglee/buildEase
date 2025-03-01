package com.examly.springapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

// Specifies that the class is an entity and is mapped to a database table
@Entity
public class User {

    // Specifies the primary key of an entity
    @Id
    
    // Specifies the primary key generation strategy
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    // Validation annotation to check if the field contains a valid email address
    @Email(message="Please enter valid Email.")
    // Validation annotation to check if the field is not empty
    @NotEmpty(message = "Email cannot be empty.")  
    private String email;


    // Validation annotation to check the size of the field
    // @NotNull(message = "Password cannot be empty.")
    @Size(min=6, message="Password must be minimum of 6 characters.")
    // // Validation annotation to check if the field is not null
    private String password;
    // Validation annotation to check if the field is not null
    @NotEmpty(message = "Username cannot be empty.")
    // @NotNull(message = "Username cannot be empty.")
    private String username;
                               
    // Validation annotation to check if the field matches the regex pattern
    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile Number must be of 10 digits.")
    // Validation annotation to check if the field is not empty
    @NotEmpty(message = "Mobile number is required.")
    private String mobileNumber;

    // Validation annotation to check if the field is not empty
    @NotEmpty(message = "User role cannot be empty.")
    private String userRole;

    // Default constructor
    public User() {
    }

    // Parameterized constructor
    public User(Long userId, String email, String password, String username, String mobileNumber, String userRole) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.username = username;
        this.mobileNumber = mobileNumber;
        this.userRole = userRole;
    }

    //Getter and Setter methods
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
