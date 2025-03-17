package com.jarvis.backend;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class PasswordUtils {
    public static byte[] hashPassword(char[] password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] passwordBytes = new byte[password.length];
        for (int i = 0; i < password.length; i++) {
            passwordBytes[i] = (byte) password[i];
        }
        byte[] hashedPassword = md.digest(passwordBytes);

        // Clear the password bytes array to remove sensitive data
        Arrays.fill(passwordBytes, (byte) 0);

        return hashedPassword;
    }

    public static boolean validatePassword(char[] password, byte[] storedHash) throws NoSuchAlgorithmException {
        byte[] hashedPassword = hashPassword(password);

        // Overwrite the password array once used
        Arrays.fill(password, '0');

        return Arrays.equals(hashedPassword, storedHash);
    }
}