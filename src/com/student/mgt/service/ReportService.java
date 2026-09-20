package com.student.mgt.service;

import com.student.mgt.model.Student;
import com.student.mgt.repository.StudentRepository;
import com.student.mgt.util.GradeCalculatorUtil;

import java.util.List;

public class ReportService {
    private final StudentRepository repository;

    public ReportService(StudentRepository repository) {
        this.repository = repository;
    }

    public void printClassSummaryReport() {
        List<Student> students = repository.findAll();
        System.out.println("=============== CLASS SUMMARY REPORT ===============");
        System.out.println("Total Registered Students: " + students.size());
        
        if (students.isEmpty()) {
            System.out.println("No records available.");
            return;
        }

        double totalAvg = 0;
        double totalAttendance = 0;
        for (Student s : students) {
            totalAvg += s.getAverageMarks();
            totalAttendance += s.getAttendancePercentage();
        }

        System.out.printf("Class Average Marks: %.2f%n", (totalAvg / students.size()));
        System.out.printf("Class Average Attendance: %.1f%%%n", (totalAttendance / students.size()));
        System.out.println("====================================================");
    }
}
