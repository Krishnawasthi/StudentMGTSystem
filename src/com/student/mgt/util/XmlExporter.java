package com.student.mgt.util;

import com.student.mgt.model.Student;
import java.util.List;

/**
 * Utility for exporting student entities to XML string format.
 */
public class XmlExporter {

    public static String toXml(Student student) {
        if (student == null) return "<student/>";
        StringBuilder sb = new StringBuilder();
        sb.append("<student>\n");
        sb.append("  <id>").append(student.getStudentId()).append("</id>\n");
        sb.append("  <rollNo>").append(escapeXml(student.getRollNo())).append("</rollNo>\n");
        sb.append("  <name>").append(escapeXml(student.getName())).append("</name>\n");
        sb.append("  <email>").append(escapeXml(student.getEmail())).append("</email>\n");
        sb.append("  <marks>").append(student.getMarks()).append("</marks>\n");
        sb.append("  <gpa>").append(student.getGpa()).append("</gpa>\n");
        sb.append("  <status>").append(student.getStatus()).append("</status>\n");
        sb.append("</student>");
        return sb.toString();
    }

    public static String toXml(List<Student> students) {
        StringBuilder sb = new StringBuilder();
        sb.append("<students>\n");
        for (Student s : students) {
            sb.append(toXml(s)).append("\n");
        }
        sb.append("</students>");
        return sb.toString();
    }

    private static String escapeXml(String input) {
        if (input == null) return "";
        return input.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&apos;");
    }
}
