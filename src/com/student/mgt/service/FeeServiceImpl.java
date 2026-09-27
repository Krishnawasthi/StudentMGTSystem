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
            case JAVA: return 1200.0;
            case PYTHON: return 1100.0;
            case DATABASE: return 1000.0;
            case WEB_DEV: return 1150.0;
            case DATA_SCIENCE: return 1500.0;
            case CYBER_SECURITY: return 1400.0;
            case AI_ML: return 1600.0;
            case CLOUD_COMPUTING: return 1350.0;
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
