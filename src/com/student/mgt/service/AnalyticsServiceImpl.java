package com.student.mgt.service;

import com.student.mgt.model.Student;
import com.student.mgt.repository.StudentRepository;
import com.student.mgt.util.MathUtil;

import java.util.List;
import java.util.stream.Collectors;

public class AnalyticsServiceImpl implements AnalyticsService {
    private final StudentRepository repository;

    public AnalyticsServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public double calculateMedianGpa() {
        List<Double> gpas = repository.findAll().stream()
                .map(Student::getGpa)
                .collect(Collectors.toList());
        return MathUtil.calculateMedian(gpas);
    }

    @Override
    public double calculateGpaStandardDeviation() {
        List<Double> gpas = repository.findAll().stream()
                .map(Student::getGpa)
                .collect(Collectors.toList());
        return MathUtil.calculateStandardDeviation(gpas);
    }

    @Override
    public double calculateStudentPercentile(String studentId) {
        List<Student> students = repository.findAll();
        if (students.isEmpty()) return 0.0;

        Student target = repository.findById(studentId).orElse(null);
        if (target == null) return 0.0;

        long lowerCount = students.stream()
                .filter(s -> s.getGpa() < target.getGpa())
                .count();

        return MathUtil.round((double) lowerCount / students.size() * 100.0, 2);
    }

    @Override
    public List<Student> getStudentsAbovePercentile(double percentileThreshold) {
        return repository.findAll().stream()
                .filter(s -> calculateStudentPercentile(s.getStudentId()) >= percentileThreshold)
                .collect(Collectors.toList());
    }
}
