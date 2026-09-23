package com.student.mgt.model;

public enum Semester {
    SEMESTER_1(1, "Fall Semester 1"),
    SEMESTER_2(2, "Spring Semester 2"),
    SEMESTER_3(3, "Fall Semester 3"),
    SEMESTER_4(4, "Spring Semester 4"),
    SEMESTER_5(5, "Fall Semester 5"),
    SEMESTER_6(6, "Spring Semester 6"),
    SEMESTER_7(7, "Fall Semester 7"),
    SEMESTER_8(8, "Spring Semester 8");

    private final int number;
    private final String description;

    Semester(int number, String description) {
        this.number = number;
        this.description = description;
    }

    public int getNumber() { return number; }
    public String getDescription() { return description; }

    public static Semester fromNumber(int num) {
        for (Semester s : values()) {
            if (s.number == num) return s;
        }
        return SEMESTER_1;
    }
}
