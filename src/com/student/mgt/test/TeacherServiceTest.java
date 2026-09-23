package com.student.mgt.test;

import com.student.mgt.model.Course;
import com.student.mgt.model.Teacher;
import com.student.mgt.repository.TeacherRepository;

import com.student.mgt.service.TeacherService;
import com.student.mgt.service.TeacherServiceImpl;

import java.io.File;
import java.util.List;

public class TeacherServiceTest {
    public static void main(String[] args) {
        System.out.println("Running TeacherService Unit Tests...");
        String testFile = "test_teachers.dat";

        try {
            TeacherRepository repo = new com.student.mgt.repository.FileTeacherRepository(testFile);
            TeacherService service = new TeacherServiceImpl(repo);

            Teacher t = service.registerTeacher("Dr. Alan Turing", "turing@university.edu", "Computer Science");
            assert t != null : "Teacher registration failed";
            System.out.println("[PASS] Teacher registration test passed");

            service.assignCourseToTeacher(t.getTeacherId(), Course.COMPUTER_SCIENCE);
            Teacher fetched = service.getTeacherById(t.getTeacherId());
            assert fetched.getAssignedCourses().contains(Course.COMPUTER_SCIENCE) : "Course assignment failed";
            System.out.println("[PASS] Teacher course assignment test passed");

            List<Teacher> csTeachers = service.getTeachersByDepartment("Computer Science");
            assert csTeachers.size() == 1 : "Department filter failed";
            System.out.println("[PASS] Department filter test passed");

            System.out.println("All TeacherService unit tests passed!");
        } catch (Exception e) {
            System.err.println("[FAIL] Test failed: " + e.getMessage());
            e.printStackTrace();
        } finally {
            new File(testFile).delete();
        }
    }
}
