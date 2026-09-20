package com.student.mgt.util;

import com.student.mgt.exception.InvalidDataException;

import java.util.regex.Pattern;

public class ValidationUtil {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    public static void validateRollNo(String rollNo) {
        if (rollNo == null || rollNo.trim().isEmpty()) {
            throw new InvalidDataException("Roll number cannot be empty.");
        }
    }

    public static void validateName(String name) {
        if (name == null || name.trim().length() < 2) {
            throw new InvalidDataException("Name must contain at least 2 characters.");
        }
    }

    public static void validateEmail(String email) {
        if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
            throw new InvalidDataException("Invalid email format.");
        }
    }

    public static void validateMarks(double marks) {
        if (marks < 0 || marks > 100) {
            throw new InvalidDataException("Marks must be between 0 and 100.");
        }
    }
}
