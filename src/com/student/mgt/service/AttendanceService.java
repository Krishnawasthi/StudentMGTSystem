package com.student.mgt.service;

import com.student.mgt.exception.StudentNotFoundException;
import com.student.mgt.model.Student;
import com.student.mgt.repository.StudentRepository;

public class AttendanceService {
    private final StudentRepository repository;

    public AttendanceService(StudentRepository repository) {
        this.repository = repository;
    }

    public void updateAttendance(String studentId, double newPercentage) throws StudentNotFoundException {
        if (newPercentage < 0 || newPercentage > 100) {
            throw new IllegalArgumentException("Attendance percentage must be between 0 and 100.");
        }
        Student student = repository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with ID: " + studentId));
        
        student.setAttendancePercentage(newPercentage);
        repository.update(student);
    }

    public boolean isEligibleForExam(Student student) {
        return student.getAttendancePercentage() >= 75.0;
    }

    public void recordBatchAttendance(java.util.List<String> studentIds, double percentage) {
        if (studentIds == null || studentIds.isEmpty()) return;
        for (String id : studentIds) {
            try {
                updateAttendance(id, percentage);
            } catch (StudentNotFoundException ignored) {}
        }
    }

    public java.util.List<Student> getLowAttendanceStudents(double threshold) {
        return repository.findAll().stream()
                .filter(s -> s.getAttendancePercentage() < threshold)
                .collect(java.util.stream.Collectors.toList());
    }
}
