package com.student.mgt.service;

import com.student.mgt.model.Course;
import com.student.mgt.model.Department;

import java.util.Arrays;
import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {

    @Override
    public List<Department> getAllDepartments() {
        return Arrays.asList(Department.values());
    }

    @Override
    public Department getDepartmentByCode(String code) {
        return Department.fromCode(code);
    }

    @Override
    public int getCourseCountInDepartment(Department department) {
        if (department == null) return 0;
        int count = 0;
        for (Course c : Course.values()) {
            if (c.name().startsWith(department.name().substring(0, 2))) {
                count++;
            }
        }
        return count > 0 ? count : 1;
    }
}
