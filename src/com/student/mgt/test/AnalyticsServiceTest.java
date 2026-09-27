package com.student.mgt.test;

import com.student.mgt.model.Course;
import com.student.mgt.model.Student;
import com.student.mgt.repository.InMemoryStudentRepository;
import com.student.mgt.service.AnalyticsService;
import com.student.mgt.service.AnalyticsServiceImpl;

public class AnalyticsServiceTest {
    public static void main(String[] args) {
        System.out.println("Running AnalyticsService Unit Verification Tests...");

        InMemoryStudentRepository repo = new InMemoryStudentRepository();
        AnalyticsService analyticsService = new AnalyticsServiceImpl(repo);

        Student s1 = new Student("1", "CS01", "Alice", "alice@test.com", Course.COMPUTER_SCIENCE);
        s1.getMarks().add(95.0); // GPA ~4.0
        Student s2 = new Student("2", "CS02", "Bob", "bob@test.com", Course.COMPUTER_SCIENCE);
        s2.getMarks().add(75.0); // GPA ~3.0

        repo.save(s1);
        repo.save(s2);

        double median = analyticsService.calculateMedianGpa();
        assert median > 0 : "Median calculation failed";
        System.out.println("[PASS] Analytics median test passed: " + median);

        double percentile = analyticsService.calculateStudentPercentile("1");
        assert percentile == 50.0 : "Percentile calculation failed, expected 50.0 got " + percentile;
        System.out.println("[PASS] Analytics percentile test passed: " + percentile + "%");

        System.out.println("All AnalyticsService unit tests passed!");
    }
}
