package com.student.mgt.model;

public enum Grade {
    A_PLUS("A+", 4.0, 90, 100),
    A("A", 3.7, 80, 89),
    B_PLUS("B+", 3.3, 75, 79),
    B("B", 3.0, 70, 74),
    C("C", 2.0, 60, 69),
    D("D", 1.0, 50, 59),
    F("F", 0.0, 0, 49);

    private final String letter;
    private final double gpaValue;
    private final int minMarks;
    private final int maxMarks;

    Grade(String letter, double gpaValue, int minMarks, int maxMarks) {
        this.letter = letter;
        this.gpaValue = gpaValue;
        this.minMarks = minMarks;
        this.maxMarks = maxMarks;
    }

    public String getLetter() { return letter; }
    public double getGpaValue() { return gpaValue; }
    public int getMinMarks() { return minMarks; }
    public int getMaxMarks() { return maxMarks; }

    public static Grade fromMarks(double marks) {
        for (Grade g : values()) {
            if (marks >= g.minMarks && marks <= g.maxMarks) {
                return g;
            }
        }
        return F;
    }
}
