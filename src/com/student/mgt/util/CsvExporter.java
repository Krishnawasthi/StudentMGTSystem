package com.student.mgt.util;

import com.student.mgt.model.Student;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvExporter {
    public static void exportStudentsToCsv(List<Student> students, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("ID,Roll No,Name,Email,Course,Avg Marks,GPA,Attendance(%)\n");
            for (Student s : students) {
                double gpa = GradeCalculatorUtil.calculateGPA(s);
                writer.write(String.format("%s,%s,\"%s\",%s,%s,%.2f,%.2f,%.1f\n",
                        s.getStudentId(),
                        s.getRollNo(),
                        s.getName(),
                        s.getEmail(),
                        s.getCourse().getDisplayName(),
                        s.getAverageMarks(),
                        gpa,
                        s.getAttendancePercentage()));
            }
        }
    }
}
