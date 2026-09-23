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
    List<Student> searchByName(String keyword);
    List<Student> filterByGpaRange(double minGpa, double maxGpa);
    List<Student> getTopPerformers(int limit);
    void updateStudentStatus(String id, com.student.mgt.model.StudentStatus status) throws StudentNotFoundException;
    List<Student> getStudentsByStatus(com.student.mgt.model.StudentStatus status);
    void updateStudentSemester(String id, com.student.mgt.model.Semester semester) throws StudentNotFoundException;
    List<Student> getStudentsBySemester(com.student.mgt.model.Semester semester);
    void addMarks(String studentId, double marks) throws StudentNotFoundException;
    void deleteStudent(String id) throws StudentNotFoundException;
}
