package com.student.mgt.service;

import com.student.mgt.model.Course;
import com.student.mgt.model.Student;
import com.student.mgt.repository.StudentRepository;

import java.util.EnumMap;
import java.util.Map;

public class CourseServiceImpl implements CourseService {
    private final StudentRepository repository;

    public CourseServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Map<Course, Integer> getCourseEnrollmentCounts() {
        Map<Course, Integer> counts = new EnumMap<>(Course.class);
        for (Course c : Course.values()) {
            counts.put(c, 0);
        }
        for (Student s : repository.findAll()) {
            Course c = s.getCourse();
            if (c != null) {
                counts.put(c, counts.getOrDefault(c, 0) + 1);
            }
        }
        return counts;
    }

    @Override
    public boolean isCourseFull(Course course, int maxCapacity) {
        return getEnrollmentCount(course) >= maxCapacity;
    }

    @Override
    public int getEnrollmentCount(Course course) {
        return getCourseEnrollmentCounts().getOrDefault(course, 0);
    }
}
