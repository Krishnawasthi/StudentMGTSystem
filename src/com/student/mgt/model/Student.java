package com.student.mgt.model;

import com.student.mgt.util.GradeCalculatorUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    private String studentId;
    private String rollNo;
    private String name;
    private String email;
    private Course course;
    private List<Double> marks;
    private double attendancePercentage;
    private StudentStatus status;
    private Semester semester;

    public Student(String studentId, String rollNo, String name, String email, Course course) {
        this.studentId = studentId;
        this.rollNo = rollNo;
        this.name = name;
        this.email = email;
        this.course = course;
        this.marks = new ArrayList<>();
        this.attendancePercentage = 100.0;
        this.status = StudentStatus.ACTIVE;
        this.semester = Semester.SEMESTER_1;
    }

    public StudentStatus getStatus() { return status != null ? status : StudentStatus.ACTIVE; }
    public void setStatus(StudentStatus status) { this.status = status; }

    public Semester getSemester() { return semester != null ? semester : Semester.SEMESTER_1; }
    public void setSemester(Semester semester) { this.semester = semester; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getRollNo() { return rollNo; }
    public void setRollNo(String rollNo) { this.rollNo = rollNo; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }

    public List<Double> getMarks() { return marks; }
    public void setMarks(List<Double> marks) { this.marks = marks; }

    public double getAttendancePercentage() { return attendancePercentage; }
    public void setAttendancePercentage(double attendancePercentage) { this.attendancePercentage = attendancePercentage; }

    public double getAverageMarks() {
        if (marks == null || marks.isEmpty()) return 0.0;
        double sum = 0;
        for (double m : marks) sum += m;
        return sum / marks.size();
    }

    public double getGpa() {
        return GradeCalculatorUtil.calculateGPA(this);
    }

    public Grade getOverallGrade() {
        return GradeCalculatorUtil.calculateGrade(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(studentId, student.studentId) || Objects.equals(rollNo, student.rollNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, rollNo);
    }

    @Override
    public String toString() {
        return String.format("Student[ID=%s, Roll=%s, Name=%s, Course=%s, AvgMarks=%.2f, Attendance=%.1f%%]",
                studentId, rollNo, name, course.getDisplayName(), getAverageMarks(), attendancePercentage);
    }
}
