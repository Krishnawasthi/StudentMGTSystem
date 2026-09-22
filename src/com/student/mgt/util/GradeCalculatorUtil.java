package com.student.mgt.util;

import com.student.mgt.model.Grade;
import com.student.mgt.model.Student;

import java.util.List;

public class GradeCalculatorUtil {

    public static Grade calculateGrade(Student student) {
        double avg = student.getAverageMarks();
        return Grade.fromMarks(avg);
    }

    public static double calculateGPA(Student student) {
        List<Double> marks = student.getMarks();
        if (marks == null || marks.isEmpty()) return 0.0;
        
        double totalGpa = 0.0;
        for (double mark : marks) {
            Grade g = Grade.fromMarks(mark);
            totalGpa += g.getGpaValue();
        }
        return totalGpa / marks.size();
    }

    public static String formatGpaDisplay(double gpa) {
        return String.format("%.2f / 4.00", gpa);
    }

    public static boolean isHonorsStudent(Student student) {
        return calculateGPA(student) >= 3.5;
    }
}
