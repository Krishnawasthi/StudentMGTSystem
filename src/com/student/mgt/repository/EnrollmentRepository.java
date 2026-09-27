package com.student.mgt.repository;

import com.student.mgt.model.CourseEnrollment;
import com.student.mgt.model.Course;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing student enrollment persistence.
 */
public interface EnrollmentRepository {
    CourseEnrollment save(CourseEnrollment enrollment);
    Optional<CourseEnrollment> findById(String enrollmentId);
    List<CourseEnrollment> findByStudentId(int studentId);
    List<CourseEnrollment> findByCourse(Course course);
    List<CourseEnrollment> findAll();
    boolean deleteById(String enrollmentId);
}
