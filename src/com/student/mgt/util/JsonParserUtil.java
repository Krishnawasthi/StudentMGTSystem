package com.student.mgt.util;

import com.student.mgt.model.Course;
import com.student.mgt.model.Student;

public class JsonParserUtil {

    public static Student parseStudentJson(String json) {
        if (json == null || !json.contains("{") || !json.contains("}")) {
            return null;
        }
        try {
            String id = extractValue(json, "studentId");
            String roll = extractValue(json, "rollNo");
            String name = extractValue(json, "name");
            String email = extractValue(json, "email");
            String courseStr = extractValue(json, "course");

            Course course = Course.COMPUTER_SCIENCE;
            if (courseStr != null && !courseStr.isEmpty()) {
                try {
                    course = Course.valueOf(courseStr);
                } catch (IllegalArgumentException ignored) {}
            }

            return new Student(id, roll, name, email, course);
        } catch (Exception e) {
            return null;
        }
    }

    private static String extractValue(String json, String key) {
        String pattern = "\"" + key + "\":\"";
        int start = json.indexOf(pattern);
        if (start == -1) return "";
        start += pattern.length();
        int end = json.indexOf("\"", start);
        if (end == -1) return "";
        return json.substring(start, end);
    }
}
