package com.student.mgt.service;

import com.student.mgt.exception.EnrollmentException;
import com.student.mgt.model.Course;
import com.student.mgt.model.CourseEnrollment;
import com.student.mgt.model.EnrollmentStatus;
import com.student.mgt.model.Semester;
import java.util.List;

/**
 * Service interface for business operations related to course enrollment.
 */
public interface EnrollmentService {
    CourseEnrollment enrollStudent(int studentId, Course course, Semester semester) throws EnrollmentException;
    CourseEnrollment updateStatus(String enrollmentId, EnrollmentStatus newStatus) throws EnrollmentException;
    CourseEnrollment updateScore(String enrollmentId, double score) throws EnrollmentException;
    List<CourseEnrollment> getEnrollmentsByStudent(int studentId);
    List<CourseEnrollment> getEnrollmentsByCourse(Course course);
}
