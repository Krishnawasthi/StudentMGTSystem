package com.student.mgt.repository;

import com.student.mgt.model.Student;
import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    void save(Student student);
    Optional<Student> findById(String id);
    Optional<Student> findByRollNo(String rollNo);
    List<Student> findAll();
    boolean deleteById(String id);
    void update(Student student);
}
