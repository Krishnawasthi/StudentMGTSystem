package com.student.mgt.test;

import com.student.mgt.exception.EnrollmentException;
import com.student.mgt.model.Course;
import com.student.mgt.model.CourseEnrollment;
import com.student.mgt.model.EnrollmentStatus;
import com.student.mgt.model.Semester;
import com.student.mgt.repository.InMemoryEnrollmentRepository;
import com.student.mgt.service.EnrollmentService;
import com.student.mgt.service.EnrollmentServiceImpl;

public class EnrollmentServiceTest {
    public static void main(String[] args) {
        testEnrollmentFlow();
        testDuplicateEnrollmentPrevented();
        testScoreUpdate();
        System.out.println("EnrollmentServiceTest passed successfully!");
    }

    private static void testEnrollmentFlow() {
        EnrollmentService service = new EnrollmentServiceImpl(new InMemoryEnrollmentRepository());
        try {
            CourseEnrollment enrollment = service.enrollStudent(101, Course.JAVA, Semester.SEMESTER_1);
            assert enrollment != null;
            assert enrollment.getStudentId() == 101;
            assert enrollment.getCourse() == Course.JAVA;
            assert enrollment.getStatus() == EnrollmentStatus.REGISTERED;

            CourseEnrollment updated = service.updateStatus(enrollment.getEnrollmentId(), EnrollmentStatus.ENROLLED);
            assert updated.getStatus() == EnrollmentStatus.ENROLLED;
        } catch (EnrollmentException e) {
            assert false : "Enrollment failed unexpectedly: " + e.getMessage();
        }
    }

    private static void testDuplicateEnrollmentPrevented() {
        EnrollmentService service = new EnrollmentServiceImpl(new InMemoryEnrollmentRepository());
        try {
            service.enrollStudent(102, Course.PYTHON, Semester.SEMESTER_2);
            boolean threw = false;
            try {
                service.enrollStudent(102, Course.PYTHON, Semester.SEMESTER_2);
            } catch (EnrollmentException e) {
                threw = true;
            }
            assert threw : "Should have thrown duplicate enrollment exception";
        } catch (EnrollmentException e) {
            assert false : "Unexpected exception: " + e.getMessage();
        }
    }

    private static void testScoreUpdate() {
        EnrollmentService service = new EnrollmentServiceImpl(new InMemoryEnrollmentRepository());
        try {
            CourseEnrollment enr = service.enrollStudent(103, Course.DATABASE, Semester.SEMESTER_3);
            service.updateScore(enr.getEnrollmentId(), 92.5);
            CourseEnrollment retrieved = service.getEnrollmentsByStudent(103).get(0);
            assert retrieved.getScore() == 92.5;
        } catch (EnrollmentException e) {
            assert false : "Unexpected exception during score update: " + e.getMessage();
        }
    }
}
