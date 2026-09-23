package com.student.mgt.service;

import com.student.mgt.model.Course;

import java.util.Map;

public interface CourseService {
    Map<Course, Integer> getCourseEnrollmentCounts();
    boolean isCourseFull(Course course, int maxCapacity);
    int getEnrollmentCount(Course course);
}
