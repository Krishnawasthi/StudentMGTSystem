package com.student.mgt.model;

/**
 * Represents the current status of a student's enrollment in a course.
 */
public enum EnrollmentStatus {
    REGISTERED("Registered"),
    ENROLLED("Enrolled"),
    COMPLETED("Completed"),
    DROPPED("Dropped"),
    WITHDRAWN("Withdrawn");

    private final String displayName;

    EnrollmentStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean isActive() {
        return this == REGISTERED || this == ENROLLED;
    }
}
