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

    public java.util.Map<com.student.mgt.model.Grade, Integer> getGradeDistribution() {
        java.util.Map<com.student.mgt.model.Grade, Integer> dist = new java.util.EnumMap<>(com.student.mgt.model.Grade.class);
        for (com.student.mgt.model.Grade g : com.student.mgt.model.Grade.values()) {
            dist.put(g, 0);
        }
        for (Student s : repository.findAll()) {
            com.student.mgt.model.Grade g = s.getOverallGrade();
            dist.put(g, dist.get(g) + 1);
        }
        return dist;
    }

    public java.util.Optional<Student> getHighestScorer() {
        return repository.findAll().stream()
                .max(java.util.Comparator.comparingDouble(Student::getAverageMarks));
    }
}
