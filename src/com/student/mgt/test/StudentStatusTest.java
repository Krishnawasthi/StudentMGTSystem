package com.student.mgt.test;

import com.student.mgt.model.Course;
import com.student.mgt.model.Semester;
import com.student.mgt.model.Student;
import com.student.mgt.model.StudentStatus;
import com.student.mgt.repository.InMemoryStudentRepository;
import com.student.mgt.service.StudentService;
import com.student.mgt.service.StudentServiceImpl;

import java.util.List;

public class StudentStatusTest {
    public static void main(String[] args) {
        System.out.println("Running StudentStatus & Semester Unit Tests...");

        InMemoryStudentRepository repo = new InMemoryStudentRepository();
        StudentService service = new StudentServiceImpl(repo);

        try {
            Student s = service.registerStudent("STU001", "David Miller", "david@test.com", Course.COMPUTER_SCIENCE);
            assert s.getStatus() == StudentStatus.ACTIVE : "Default status should be ACTIVE";
            assert s.getSemester() == Semester.SEMESTER_1 : "Default semester should be SEMESTER_1";

            service.updateStudentStatus(s.getStudentId(), StudentStatus.SUSPENDED);
            assert service.getStudentById(s.getStudentId()).getStatus() == StudentStatus.SUSPENDED : "Status update failed";
            System.out.println("[PASS] Student status update test passed");

            service.updateStudentSemester(s.getStudentId(), Semester.SEMESTER_4);
            assert service.getStudentById(s.getStudentId()).getSemester() == Semester.SEMESTER_4 : "Semester update failed";
            System.out.println("[PASS] Student semester update test passed");

            List<Student> sem4Students = service.getStudentsBySemester(Semester.SEMESTER_4);
            assert sem4Students.size() == 1 : "Expected 1 student in semester 4";
            System.out.println("[PASS] Filter by semester test passed");

            System.out.println("All StudentStatus and Semester unit tests passed!");
        } catch (Exception e) {
            System.err.println("[FAIL] Test failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
