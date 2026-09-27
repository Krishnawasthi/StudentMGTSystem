package com.student.mgt.service;

import com.student.mgt.model.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> getAllDepartments();
    Department getDepartmentByCode(String code);
    int getCourseCountInDepartment(Department department);
}
