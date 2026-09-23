package com.student.mgt.service;

import com.student.mgt.exception.DuplicateStudentException;
import com.student.mgt.exception.StudentNotFoundException;
import com.student.mgt.model.Course;
import com.student.mgt.model.Student;
import com.student.mgt.repository.StudentRepository;
import com.student.mgt.util.ValidationUtil;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class StudentServiceImpl implements StudentService {
    private final StudentRepository repository;

    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Student registerStudent(String rollNo, String name, String email, Course course) throws DuplicateStudentException {
        ValidationUtil.validateRollNo(rollNo);
        ValidationUtil.validateName(name);
        ValidationUtil.validateEmail(email);

        if (repository.findByRollNo(rollNo).isPresent()) {
            throw new DuplicateStudentException("Student with roll number " + rollNo + " already exists.");
        }

        String id = UUID.randomUUID().toString().substring(0, 8);
        Student student = new Student(id, rollNo, name, email, course);
        repository.save(student);
        return student;
    }

    @Override
    public Student getStudentById(String id) throws StudentNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with ID: " + id));
    }

    @Override
    public Student getStudentByRollNo(String rollNo) throws StudentNotFoundException {
        return repository.findByRollNo(rollNo)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with Roll No: " + rollNo));
    }

    @Override
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    @Override
    public List<Student> getStudentsByCourse(Course course) {
        return repository.findAll().stream()
                .filter(s -> s.getCourse() == course)
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> searchByName(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllStudents();
        }
        String term = keyword.trim().toLowerCase();
        return repository.findAll().stream()
                .filter(s -> s.getName().toLowerCase().contains(term))
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> filterByGpaRange(double minGpa, double maxGpa) {
        return repository.findAll().stream()
                .filter(s -> s.getGpa() >= minGpa && s.getGpa() <= maxGpa)
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> getTopPerformers(int limit) {
        return repository.findAll().stream()
                .sorted((s1, s2) -> Double.compare(s2.getGpa(), s1.getGpa()))
                .limit(limit > 0 ? limit : 5)
                .collect(Collectors.toList());
    }

    @Override
    public void updateStudentStatus(String id, com.student.mgt.model.StudentStatus status) throws StudentNotFoundException {
        Student student = getStudentById(id);
        student.setStatus(status);
        repository.update(student);
    }

    @Override
    public List<Student> getStudentsByStatus(com.student.mgt.model.StudentStatus status) {
        return repository.findAll().stream()
                .filter(s -> s.getStatus() == status)
                .collect(Collectors.toList());
    }

    @Override
    public void updateStudentSemester(String id, com.student.mgt.model.Semester semester) throws StudentNotFoundException {
        Student student = getStudentById(id);
        student.setSemester(semester);
        repository.update(student);
    }

    @Override
    public List<Student> getStudentsBySemester(com.student.mgt.model.Semester semester) {
        return repository.findAll().stream()
                .filter(s -> s.getSemester() == semester)
                .collect(Collectors.toList());
    }

    @Override
    public void addMarks(String studentId, double marks) throws StudentNotFoundException {
        ValidationUtil.validateMarks(marks);
        Student student = getStudentById(studentId);
        student.getMarks().add(marks);
        repository.update(student);
    }

    @Override
    public void deleteStudent(String id) throws StudentNotFoundException {
        boolean deleted = repository.deleteById(id);
        if (!deleted) {
            throw new StudentNotFoundException("Could not delete. Student not found with ID: " + id);
        }
    }
}
