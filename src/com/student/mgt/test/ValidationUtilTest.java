package com.student.mgt.test;

import com.student.mgt.exception.InvalidDataException;
import com.student.mgt.model.Course;
import com.student.mgt.model.Grade;
import com.student.mgt.model.Student;
import com.student.mgt.util.GradeCalculatorUtil;
import com.student.mgt.util.ValidationUtil;

public class ValidationUtilTest {
    public static void main(String[] args) {
        System.out.println("Running ValidationUtil and GradeCalculatorUtil Tests...");

        // Test roll number validation
        try {
            ValidationUtil.validateRollNo("");
            assert false : "Empty roll number should throw exception";
        } catch (InvalidDataException e) {
            System.out.println("[PASS] Roll number validation passed");
        }

        // Test email validation
        try {
            ValidationUtil.validateEmail("invalid-email");
            assert false : "Invalid email should throw exception";
        } catch (InvalidDataException e) {
            System.out.println("[PASS] Email validation passed");
        }

        // Test age validation
        try {
            ValidationUtil.validateAge(10);
            assert false : "Age < 15 should throw exception";
        } catch (InvalidDataException e) {
            System.out.println("[PASS] Age validation passed");
        }

        // Test GPA calculation
        Student s = new Student("S1", "CS01", "Alice", "alice@example.com", Course.COMPUTER_SCIENCE);
        s.getMarks().add(95.0); // Grade A+ (4.0)
        s.getMarks().add(85.0); // Grade A (3.7)
        double gpa = GradeCalculatorUtil.calculateGPA(s);
        assert Math.abs(gpa - 3.85) < 0.01 : "GPA calculation failed, expected ~3.85 but got " + gpa;
        System.out.println("[PASS] GPA calculation test passed: " + GradeCalculatorUtil.formatGpaDisplay(gpa));

        assert GradeCalculatorUtil.isHonorsStudent(s) : "Alice should be an honors student";
        System.out.println("[PASS] Honors status test passed");

        System.out.println("All ValidationUtil and GradeCalculatorUtil tests passed!");
    }
}
