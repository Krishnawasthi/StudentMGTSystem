package com.student.mgt.service;

import com.student.mgt.model.Course;

/**
 * Service interface for computing tuition fees and scholarship discounts.
 */
public interface FeeService {
    double getBaseFeePerCourse(Course course);
    double calculateTuition(Course course, double gpa);
    double applyMeritDiscount(double fee, double gpa);
}
