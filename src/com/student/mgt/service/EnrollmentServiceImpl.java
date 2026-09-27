package com.student.mgt.service;

import com.student.mgt.exception.EnrollmentException;
import com.student.mgt.model.Course;
import com.student.mgt.model.CourseEnrollment;
import com.student.mgt.model.EnrollmentStatus;
import com.student.mgt.model.Semester;
import com.student.mgt.repository.EnrollmentRepository;
import java.util.List;
import java.util.UUID;

/**
 * Business implementation of EnrollmentService.
 */
public class EnrollmentServiceImpl implements EnrollmentService {
    private final EnrollmentRepository repository;

    public EnrollmentServiceImpl(EnrollmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public CourseEnrollment enrollStudent(int studentId, Course course, Semester semester) throws EnrollmentException {
        if (studentId <= 0) {
            throw new EnrollmentException("Invalid student ID: " + studentId);
        }
        if (course == null || semester == null) {
            throw new EnrollmentException("Course and Semester must not be null");
        }
        List<CourseEnrollment> existing = repository.findByStudentId(studentId);
        for (CourseEnrollment e : existing) {
            if (e.getCourse() == course && e.getSemester() == semester && e.getStatus().isActive()) {
                throw new EnrollmentException("Student is already enrolled in " + course + " for " + semester);
            }
        }
        String id = "ENR-" + UUID.randomUUID().toString().substring(0, 8);
        CourseEnrollment enrollment = new CourseEnrollment(id, studentId, course, semester);
        return repository.save(enrollment);
    }

    @Override
    public CourseEnrollment updateStatus(String enrollmentId, EnrollmentStatus newStatus) throws EnrollmentException {
        CourseEnrollment enrollment = repository.findById(enrollmentId)
                .orElseThrow(() -> new EnrollmentException("Enrollment record not found: " + enrollmentId));
        enrollment.setStatus(newStatus);
        return repository.save(enrollment);
    }

    @Override
    public CourseEnrollment updateScore(String enrollmentId, double score) throws EnrollmentException {
        if (score < 0.0 || score > 100.0) {
            throw new EnrollmentException("Score must be between 0.0 and 100.0");
        }
        CourseEnrollment enrollment = repository.findById(enrollmentId)
                .orElseThrow(() -> new EnrollmentException("Enrollment record not found: " + enrollmentId));
        enrollment.setScore(score);
        return repository.save(enrollment);
    }

    @Override
    public List<CourseEnrollment> getEnrollmentsByStudent(int studentId) {
        return repository.findByStudentId(studentId);
    }

    @Override
    public List<CourseEnrollment> getEnrollmentsByCourse(Course course) {
        return repository.findByCourse(course);
    }
}
