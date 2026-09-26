package com.student.mgt.util;

/**
 * Utility class for common string operations used throughout the application.
 */
public class StringUtil {

    private StringUtil() {
        // Utility class, prevent instantiation
    }

    /**
     * Checks if a string is null or empty (after trimming).
     */
    public static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * Checks if a string is not null and not empty (after trimming).
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * Capitalizes the first letter of a string.
     */
    public static String capitalize(String str) {
        if (isBlank(str)) return str;
        String trimmed = str.trim();
        return trimmed.substring(0, 1).toUpperCase() + trimmed.substring(1).toLowerCase();
    }

    /**
     * Capitalizes each word in a string.
     */
    public static String capitalizeWords(String str) {
        if (isBlank(str)) return str;
        String[] words = str.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if (sb.length() > 0) sb.append(' ');
            sb.append(capitalize(word));
        }
        return sb.toString();
    }

    /**
     * Truncates a string to the given max length, appending "..." if truncated.
     */
    public static String truncate(String str, int maxLength) {
        if (str == null || str.length() <= maxLength) return str;
        return str.substring(0, maxLength - 3) + "...";
    }

    /**
     * Masks an email for display (e.g., j***@example.com).
     */
    public static String maskEmail(String email) {
        if (isBlank(email) || !email.contains("@")) return email;
        int atIndex = email.indexOf('@');
        if (atIndex <= 1) return email;
        return email.charAt(0) + "***" + email.substring(atIndex);
    }
}
