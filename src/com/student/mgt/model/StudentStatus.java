package com.student.mgt.model;

public enum StudentStatus {
    ACTIVE("Active"),
    SUSPENDED("Suspended"),
    GRADUATED("Graduated"),
    DROPPED("Dropped Out");

    private final String displayName;

    StudentStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
