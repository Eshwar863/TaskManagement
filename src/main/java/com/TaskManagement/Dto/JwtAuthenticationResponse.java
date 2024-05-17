package com.TaskManagement.Dto;

public class JwtAuthenticationResponse {
    private String token;
    private String tokenType = "Bearer";

    public JwtAuthenticationResponse(String token){
        this.token = token;
    }
}
