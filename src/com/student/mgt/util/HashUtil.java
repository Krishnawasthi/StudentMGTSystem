package com.student.mgt.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

/**
 * Utility class for generating cryptographic hashes and checksums.
 */
public class HashUtil {

    public static String sha256(String input) {
        return hash(input, "SHA-256");
    }

    public static String md5(String input) {
        return hash(input, "MD5");
    }

    private static String hash(String input, String algorithm) {
        if (input == null) return "";
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            byte[] bytes = md.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Algorithm not found: " + algorithm, e);
        }
    }
}
