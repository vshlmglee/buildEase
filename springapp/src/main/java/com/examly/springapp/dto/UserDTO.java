package com.examly.springapp.dto;

public class UserDTO {

    private String token;

    public UserDTO(){}
    public UserDTO(String token) {
        this.token = token;
        
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

}
 