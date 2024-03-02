package br.com.erudio.restwithspringbootandjavaerudio.services;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class Decoder {
    public static void main(String[] args) {
        // Given password hash
        String passwordHash = "1e3cdeeaaaeeda173ff6d002e7cb5e3f91ebc354dcff52156c9eaba1793a3a5e5bee306c11099e22";

        // Given salt (example, replace with your actual salt)
        byte[] salt = "salt123".getBytes();

        // PBKDF2 parameters
        int iterations = 1000;  // Example iteration count, replace with your actual iteration count
        int keyLength = 128;   // Example key length, replace with your actual key length

        try {
            // Create PBEKeySpec with password, salt, iteration count, and key length
            KeySpec spec = new PBEKeySpec(passwordHash.toCharArray(), salt, iterations, keyLength);

            // Get SecretKeyFactory instance for PBKDF2WithHmacSHA1 algorithm
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

            // Generate secret key
            byte[] decodedKey = factory.generateSecret(spec).getEncoded();

            // Print the decoded key
            System.out.println("Decoded key: " + bytesToHex(decodedKey));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }

    // Helper method to convert byte array to hexadecimal string
    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}
