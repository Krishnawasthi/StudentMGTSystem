package com.student.mgt.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Model representing a student's enrollment record for a specific course.
 */
public class CourseEnrollment implements Serializable {
    private static final long serialVersionUID = 1L;

    private String enrollmentId;
    private int studentId;
    private Course course;
    private Semester semester;
    private EnrollmentStatus status;
    private double score;
    private LocalDate enrollmentDate;

    public CourseEnrollment(String enrollmentId, int studentId, Course course, Semester semester) {
        this.enrollmentId = enrollmentId;
        this.studentId = studentId;
        this.course = course;
        this.semester = semester;
        this.status = EnrollmentStatus.REGISTERED;
        this.score = 0.0;
        this.enrollmentDate = LocalDate.now();
    }

    public String getEnrollmentId() { return enrollmentId; }
    public void setEnrollmentId(String enrollmentId) { this.enrollmentId = enrollmentId; }

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }

    public Semester getSemester() { return semester; }
    public void setSemester(Semester semester) { this.semester = semester; }

    public EnrollmentStatus getStatus() { return status; }
    public void setStatus(EnrollmentStatus status) { this.status = status; }

    public double getScore() { return score; }
    public void setScore(double score) { this.score = score; }

    public LocalDate getEnrollmentDate() { return enrollmentDate; }
    public void setEnrollmentDate(LocalDate enrollmentDate) { this.enrollmentDate = enrollmentDate; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseEnrollment that = (CourseEnrollment) o;
        return studentId == that.studentId && Objects.equals(enrollmentId, that.enrollmentId) && course == that.course;
    }

    @Override
    public int hashCode() {
        return Objects.hash(enrollmentId, studentId, course);
    }

    @Override
    public String toString() {
        return "CourseEnrollment{" +
                "enrollmentId='" + enrollmentId + '\'' +
                ", studentId=" + studentId +
                ", course=" + course +
                ", semester=" + semester +
                ", status=" + status +
                ", score=" + score +
                '}';
    }
}
