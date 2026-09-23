package com.student.mgt.service;

import com.student.mgt.model.Course;
import com.student.mgt.model.Teacher;

import java.util.List;

public interface TeacherService {
    Teacher registerTeacher(String name, String email, String department);
    Teacher getTeacherById(String id);
    List<Teacher> getAllTeachers();
    List<Teacher> getTeachersByDepartment(String department);
    void assignCourseToTeacher(String teacherId, Course course);
}
