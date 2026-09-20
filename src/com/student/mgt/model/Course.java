package com.student.mgt.model;

public enum Course {
    COMPUTER_SCIENCE("CS", "Computer Science"),
    INFORMATION_TECHNOLOGY("IT", "Information Technology"),
    ELECTRICAL_ENGINEERING("EE", "Electrical Engineering"),
    MECHANICAL_ENGINEERING("ME", "Mechanical Engineering"),
    CIVIL_ENGINEERING("CE", "Civil Engineering");

    private final String code;
    private final String displayName;

    Course(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    public String getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName;
    }
}
