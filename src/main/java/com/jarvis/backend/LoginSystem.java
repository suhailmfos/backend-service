package com.jarvis.backend;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LoginSystem {
    private final Map<String, byte[]> userDatabase = new HashMap<>();

    public void registerUser(String username, char[] password) throws NoSuchAlgorithmException {
        byte[] hashedPassword = PasswordUtils.hashPassword(password);
        userDatabase.put(username, hashedPassword);

        // Clear the password array to remove sensitive data
        Arrays.fill(password, '0');
    }

    public boolean loginUser(String username, char[] password) throws NoSuchAlgorithmException {
        byte[] storedHash = userDatabase.get(username);
        if (storedHash == null) {
            return false;
        }
        return PasswordUtils.validatePassword(password, storedHash);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        LoginSystem loginSystem = new LoginSystem();

        // Register a user
        char[] password = {'s', 'e', 'c', 'r', 'e', 't'};
        loginSystem.registerUser("john_doe", password);

        // Attempt to login
        char[] loginPassword = {'s', 'e', 'c', 'r', 'e', 't'};
        boolean loginSuccess = loginSystem.loginUser("john_doe", loginPassword);

        if (loginSuccess) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Login failed!");
        }

        // Clear the login password array to remove sensitive data
        Arrays.fill(loginPassword, '0');
    }
}
