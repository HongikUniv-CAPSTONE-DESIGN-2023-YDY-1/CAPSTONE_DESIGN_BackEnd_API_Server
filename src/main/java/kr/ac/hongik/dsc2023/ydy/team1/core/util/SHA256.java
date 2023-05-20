package kr.ac.hongik.dsc2023.ydy.team1.core.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256{

    public static String hash(String input) {
        try {
            // Create a MessageDigest instance for SHA-256
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");

            // Compute the hash value
            byte[] hash = sha256.digest(input.getBytes());

            // Convert the hash bytes to hexadecimal representation
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            // Return the resulting hash
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
