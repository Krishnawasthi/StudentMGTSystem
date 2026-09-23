package com.student.mgt.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Teacher implements Serializable {
    private static final long serialVersionUID = 1L;

    private String teacherId;
    private String name;
    private String email;
    private String department;
    private List<Course> assignedCourses;

    public Teacher(String teacherId, String name, String email, String department) {
        this.teacherId = teacherId;
        this.name = name;
        this.email = email;
        this.department = department;
        this.assignedCourses = new ArrayList<>();
    }

    public String getTeacherId() { return teacherId; }
    public void setTeacherId(String teacherId) { this.teacherId = teacherId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public List<Course> getAssignedCourses() { return assignedCourses; }
    public void setAssignedCourses(List<Course> assignedCourses) { this.assignedCourses = assignedCourses; }

    public void assignCourse(Course course) {
        if (course != null && !assignedCourses.contains(course)) {
            assignedCourses.add(course);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(teacherId, teacher.teacherId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherId);
    }

    @Override
    public String toString() {
        return String.format("Teacher[ID=%s, Name=%s, Dept=%s, CoursesCount=%d]",
                teacherId, name, department, assignedCourses.size());
    }
}
