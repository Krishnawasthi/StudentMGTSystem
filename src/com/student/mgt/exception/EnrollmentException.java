package com.student.mgt.exception;

/**
 * Exception thrown when course enrollment rules or status transitions are violated.
 */
public class EnrollmentException extends Exception {
    public EnrollmentException(String message) {
        super(message);
    }

    public EnrollmentException(String message, Throwable cause) {
        super(message, cause);
    }
}
