package com.student.mgt.model;

public class GradePointScale {

    public static double convertPercentageToGpa(double percentage) {
        if (percentage >= 90.0) return 4.0;
        if (percentage >= 85.0) return 3.7;
        if (percentage >= 80.0) return 3.3;
        if (percentage >= 75.0) return 3.0;
        if (percentage >= 70.0) return 2.7;
        if (percentage >= 65.0) return 2.3;
        if (percentage >= 60.0) return 2.0;
        if (percentage >= 50.0) return 1.0;
        return 0.0;
    }

    public static boolean isPassingGpa(double gpa) {
        return gpa >= 2.0;
    }
}
