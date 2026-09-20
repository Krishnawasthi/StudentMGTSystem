package com.student.mgt.service;

import com.student.mgt.exception.DuplicateStudentException;
import com.student.mgt.exception.StudentNotFoundException;
import com.student.mgt.model.Course;
import com.student.mgt.model.Student;

import java.util.List;

public interface StudentService {
    Student registerStudent(String rollNo, String name, String email, Course course) throws DuplicateStudentException;
    Student getStudentById(String id) throws StudentNotFoundException;
    Student getStudentByRollNo(String rollNo) throws StudentNotFoundException;
    List<Student> getAllStudents();
    List<Student> getStudentsByCourse(Course course);
    void addMarks(String studentId, double marks) throws StudentNotFoundException;
    void deleteStudent(String id) throws StudentNotFoundException;
}
