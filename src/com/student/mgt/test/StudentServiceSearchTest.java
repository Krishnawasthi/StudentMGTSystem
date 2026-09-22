package com.student.mgt.test;

import com.student.mgt.model.Course;
import com.student.mgt.model.Student;
import com.student.mgt.repository.InMemoryStudentRepository;

import com.student.mgt.service.StudentService;
import com.student.mgt.service.StudentServiceImpl;

import java.util.List;

public class StudentServiceSearchTest {
    public static void main(String[] args) {
        System.out.println("Running StudentService Search and Filter Unit Tests...");

        InMemoryStudentRepository repo = new InMemoryStudentRepository();
        StudentService service = new StudentServiceImpl(repo);

        try {
            Student s1 = service.registerStudent("R101", "Alice Johnson", "alice@test.com", Course.COMPUTER_SCIENCE);
            Student s2 = service.registerStudent("R102", "Bob Smith", "bob@test.com", Course.INFORMATION_TECHNOLOGY);
            Student s3 = service.registerStudent("R103", "Charlie Johnson", "charlie@test.com", Course.COMPUTER_SCIENCE);

            service.addMarks(s1.getStudentId(), 95.0); // GPA 4.0
            service.addMarks(s2.getStudentId(), 75.0); // GPA ~3.3
            service.addMarks(s3.getStudentId(), 55.0); // GPA ~1.0

            // Search by name
            List<Student> Johnsons = service.searchByName("johnson");
            assert Johnsons.size() == 2 : "Expected 2 Johnsons, found " + Johnsons.size();
            System.out.println("[PASS] Search by name test passed");

            // Filter by GPA range
            List<Student> highGpaStudents = service.filterByGpaRange(3.0, 4.0);
            assert highGpaStudents.size() == 2 : "Expected 2 high GPA students";
            System.out.println("[PASS] Filter by GPA range test passed");

            // Top performers
            List<Student> top2 = service.getTopPerformers(2);
            assert top2.size() == 2 : "Expected 2 top performers";
            assert top2.get(0).getRollNo().equals("R101") : "Alice should be top performer";
            System.out.println("[PASS] Top performers ranking test passed");

            System.out.println("All search and filter tests passed successfully!");
        } catch (Exception e) {
            System.err.println("[FAIL] Test failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
