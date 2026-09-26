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

    @Override
    public void deleteTeacher(String id) {
        Teacher teacher = getTeacherById(id);
        if (teacher == null) {
            throw new com.student.mgt.exception.TeacherNotFoundException("Teacher not found with ID: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public void updateTeacherDetails(String id, String name, String email, String department) {
        Teacher teacher = getTeacherById(id);
        if (teacher == null) {
            throw new com.student.mgt.exception.TeacherNotFoundException("Teacher not found with ID: " + id);
        }
        if (name != null && !name.trim().isEmpty()) {
            ValidationUtil.validateName(name);
            teacher.setName(name);
        }
        if (email != null && !email.trim().isEmpty()) {
            ValidationUtil.validateEmail(email);
            teacher.setEmail(email);
        }
        if (department != null && !department.trim().isEmpty()) {
            ValidationUtil.validateDepartment(department);
            teacher.setDepartment(department);
        }
        repository.update(teacher);
    }

    @Override
    public List<Teacher> searchByName(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllTeachers();
        }
        String term = keyword.trim().toLowerCase();
        return repository.findAll().stream()
                .filter(t -> t.getName().toLowerCase().contains(term))
                .collect(Collectors.toList());
    }
}
