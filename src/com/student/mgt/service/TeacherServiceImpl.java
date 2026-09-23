package com.student.mgt.service;

import com.student.mgt.model.Course;
import com.student.mgt.model.Teacher;
import com.student.mgt.repository.TeacherRepository;
import com.student.mgt.util.ValidationUtil;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository repository;

    public TeacherServiceImpl(TeacherRepository repository) {
        this.repository = repository;
    }

    @Override
    public Teacher registerTeacher(String name, String email, String department) {
        ValidationUtil.validateName(name);
        ValidationUtil.validateEmail(email);

        String id = "TCH-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        Teacher teacher = new Teacher(id, name, email, department);
        repository.save(teacher);
        return teacher;
    }

    @Override
    public Teacher getTeacherById(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return repository.findAll();
    }

    @Override
    public List<Teacher> getTeachersByDepartment(String department) {
        if (department == null) return getAllTeachers();
        return repository.findAll().stream()
                .filter(t -> department.equalsIgnoreCase(t.getDepartment()))
                .collect(Collectors.toList());
    }

    @Override
    public void assignCourseToTeacher(String teacherId, Course course) {
        Teacher teacher = getTeacherById(teacherId);
        if (teacher != null && course != null) {
            teacher.assignCourse(course);
            repository.update(teacher);
        }
    }
}
