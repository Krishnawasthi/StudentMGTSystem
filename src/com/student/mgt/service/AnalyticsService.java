package com.student.mgt.service;

import com.student.mgt.model.Student;

import java.util.List;

public interface AnalyticsService {
    double calculateMedianGpa();
    double calculateGpaStandardDeviation();
    double calculateStudentPercentile(String studentId);
    List<Student> getStudentsAbovePercentile(double percentile);
}
