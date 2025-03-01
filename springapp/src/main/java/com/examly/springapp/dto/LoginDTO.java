package com.examly.springapp.dto;

public class LoginDTO {

    

    
    // Validation annotation to check if the password field is not empty
    // @NotEmpty(message = "Password can't be empty.")
    // @Size(min = 8, message = "Password must be at least 8 characters long.")
    private String password;

    // @Email(message = "Email must be valid")
    private String email;

    
    public LoginDTO(){} // Default constructor
      
    public LoginDTO(String password, String email) { // Parameterized constructor
   

        this.password = password;
        this.email = email;
    }

    //Getter and Setter methods
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    
    

}

