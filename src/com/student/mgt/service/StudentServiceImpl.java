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
