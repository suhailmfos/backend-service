package com.jarvis.backend.model;


import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}