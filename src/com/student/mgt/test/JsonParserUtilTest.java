package com.student.mgt.test;

import com.student.mgt.model.Course;
import com.student.mgt.model.Student;
import com.student.mgt.util.DataExportUtil;
import com.student.mgt.util.JsonParserUtil;

public class JsonParserUtilTest {
    public static void main(String[] args) {
        System.out.println("Running JsonParserUtil Unit Verification Tests...");

        Student s = new Student("STU-100", "R999", "Json User", "json@example.com", Course.INFORMATION_TECHNOLOGY);
        String jsonStr = DataExportUtil.toJsonString(s);

        Student parsed = JsonParserUtil.parseStudentJson(jsonStr);
        assert parsed != null : "JSON parsing failed";
        assert "STU-100".equals(parsed.getStudentId()) : "Parsed student ID mismatch";
        assert "R999".equals(parsed.getRollNo()) : "Parsed roll number mismatch";

        System.out.println("[PASS] JsonParserUtil serialization-deserialization roundtrip passed");
        System.out.println("All JsonParserUtil unit tests passed!");
    }
}
