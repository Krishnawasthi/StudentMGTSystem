package com.student.mgt.model;

public enum Course {
    COMPUTER_SCIENCE("CS", "Computer Science"),
    INFORMATION_TECHNOLOGY("IT", "Information Technology"),
    ELECTRICAL_ENGINEERING("EE", "Electrical Engineering"),
    MECHANICAL_ENGINEERING("ME", "Mechanical Engineering"),
    CIVIL_ENGINEERING("CE", "Civil Engineering"),
    ELECTRONICS_ENGINEERING("ECE", "Electronics Engineering"),
    CHEMICAL_ENGINEERING("CHE", "Chemical Engineering");

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

    public static Course fromCode(String code) {
        for (Course c : values()) {
            if (c.code.equalsIgnoreCase(code)) {
                return c;
            }
        }
        throw new IllegalArgumentException("No course found with code: " + code);
    }

    public static Course fromDisplayName(String name) {
        for (Course c : values()) {
            if (c.displayName.equalsIgnoreCase(name)) {
                return c;
            }
        }
        throw new IllegalArgumentException("No course found with name: " + name);
    }
}
