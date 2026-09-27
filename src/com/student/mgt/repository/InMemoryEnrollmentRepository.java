package com.student.mgt.repository;

import com.student.mgt.model.CourseEnrollment;
import com.student.mgt.model.Course;
import java.util.*;
import java.util.stream.Collectors;

/**
 * In-memory implementation of EnrollmentRepository.
 */
public class InMemoryEnrollmentRepository implements EnrollmentRepository {
    private final Map<String, CourseEnrollment> store = new HashMap<>();

    @Override
    public CourseEnrollment save(CourseEnrollment enrollment) {
        store.put(enrollment.getEnrollmentId(), enrollment);
        return enrollment;
    }

    @Override
    public Optional<CourseEnrollment> findById(String enrollmentId) {
        return Optional.ofNullable(store.get(enrollmentId));
    }

    @Override
    public List<CourseEnrollment> findByStudentId(int studentId) {
        return store.values().stream()
                .filter(e -> e.getStudentId() == studentId)
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseEnrollment> findByCourse(Course course) {
        return store.values().stream()
                .filter(e -> e.getCourse() == course)
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseEnrollment> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public boolean deleteById(String enrollmentId) {
        return store.remove(enrollmentId) != null;
    }
}
