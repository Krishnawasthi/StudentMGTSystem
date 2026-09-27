package com.student.mgt.model;

public enum Department {
    COMPUTER_SCIENCE("CS", "Department of Computer Science"),
    INFORMATION_TECHNOLOGY("IT", "Department of Information Technology"),
    ELECTRICAL_ENGINEERING("EE", "Department of Electrical Engineering"),
    MECHANICAL_ENGINEERING("ME", "Department of Mechanical Engineering"),
    CIVIL_ENGINEERING("CE", "Department of Civil Engineering");

    private final String code;
    private final String fullName;

    Department(String code, String fullName) {
        this.code = code;
        this.fullName = fullName;
    }

    public String getCode() { return code; }
    public String getFullName() { return fullName; }

    public static Department fromCode(String code) {
        if (code == null) return COMPUTER_SCIENCE;
        for (Department d : values()) {
            if (d.code.equalsIgnoreCase(code.trim())) return d;
        }
        return COMPUTER_SCIENCE;
    }
}
