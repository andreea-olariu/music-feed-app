package com.example.backend.utils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Hasher {

    public static String sha256(String input) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(input.getBytes());

        // Convert byte array to hex string
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }

}
