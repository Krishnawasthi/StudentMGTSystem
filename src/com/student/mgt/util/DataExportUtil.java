package com.student.mgt.util;

import com.student.mgt.model.Student;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DataExportUtil {

    public static String toJsonString(Student student) {
        if (student == null) return "{}";
        return String.format(
                "{\"studentId\":\"%s\",\"rollNo\":\"%s\",\"name\":\"%s\",\"email\":\"%s\",\"course\":\"%s\",\"gpa\":%.2f,\"attendance\":%.1f}",
                student.getStudentId(),
                student.getRollNo(),
                student.getName(),
                student.getEmail(),
                student.getCourse().name(),
                student.getGpa(),
                student.getAttendancePercentage()
        );
    }

    public static String toJsonArray(List<Student> students) {
        if (students == null || students.isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder("[\n");
        for (int i = 0; i < students.size(); i++) {
            sb.append("  ").append(toJsonString(students.get(i)));
            if (i < students.size() - 1) {
                sb.append(",");
            }
            sb.append("\n");
        }
        sb.append("]");
        return sb.toString();
    }

    public static void exportToJsonFile(List<Student> students, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(toJsonArray(students));
        }
    }
}
