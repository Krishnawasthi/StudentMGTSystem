package com.student.mgt.service;

import com.student.mgt.model.Course;

/**
 * Implementation of FeeService providing tuition and merit discount logic.
 */
public class FeeServiceImpl implements FeeService {

    @Override
    public double getBaseFeePerCourse(Course course) {
        if (course == null) return 1000.0;
        switch (course) {
            case COMPUTER_SCIENCE: return 1500.0;
            case INFORMATION_TECHNOLOGY: return 1400.0;
            case ELECTRICAL_ENGINEERING: return 1300.0;
            case MECHANICAL_ENGINEERING: return 1250.0;
            case CIVIL_ENGINEERING: return 1200.0;
            case ELECTRONICS_ENGINEERING: return 1350.0;
            case CHEMICAL_ENGINEERING: return 1250.0;
            default: return 1000.0;
        }
    }

    @Override
    public double calculateTuition(Course course, double gpa) {
        double baseFee = getBaseFeePerCourse(course);
        return applyMeritDiscount(baseFee, gpa);
    }

    @Override
    public double applyMeritDiscount(double fee, double gpa) {
        if (gpa >= 3.8) {
            return fee * 0.75; // 25% scholarship
        } else if (gpa >= 3.5) {
            return fee * 0.85; // 15% scholarship
        } else if (gpa >= 3.0) {
            return fee * 0.95; // 5% scholarship
        }
        return fee;
    }
}
