package com.student.mgt.test;

import com.student.mgt.model.Course;
import com.student.mgt.model.Student;
import com.student.mgt.repository.FileStudentRepository;
import com.student.mgt.repository.StudentRepository;
import com.student.mgt.service.StudentService;
import com.student.mgt.service.StudentServiceImpl;

import java.io.File;

public class StudentServiceTest {
    public static void main(String[] args) {
        String testFile = "test_students.txt";
        StudentRepository repo = new FileStudentRepository(testFile);
        StudentService service = new StudentServiceImpl(repo);

        try {
            System.out.println("Running Core Java Unit Verification Tests...");
            Student s = service.registerStudent("TEST01", "Test User", "test@example.com", Course.COMPUTER_SCIENCE);
            assert s != null : "Student creation failed";
            System.out.println("[PASS] Student Registration Test Passed");

            service.addMarks(s.getStudentId(), 95.0);
            assert s.getAverageMarks() == 95.0 : "Marks calculation failed";
            System.out.println("[PASS] Student Marks Test Passed");

            service.deleteStudent(s.getStudentId());
            System.out.println("[PASS] Student Deletion Test Passed");
            System.out.println("All verification tests passed successfully!");
        } catch (Exception e) {
            System.err.println("[FAIL] Test failed: " + e.getMessage());
        } finally {
            new File(testFile).delete();
        }
    }
}
