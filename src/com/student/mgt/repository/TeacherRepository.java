package com.student.mgt.repository;

import com.student.mgt.model.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository {
    void save(Teacher teacher);
    Optional<Teacher> findById(String id);
    List<Teacher> findAll();
    boolean deleteById(String id);
    void update(Teacher teacher);
}
