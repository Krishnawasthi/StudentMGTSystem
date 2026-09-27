package com.student.mgt.test;

import com.student.mgt.model.Course;
import com.student.mgt.model.Student;
import com.student.mgt.util.XmlExporter;
import java.util.Arrays;

public class XmlExporterTest {
    public static void main(String[] args) {
        testSingleStudentXml();
        testStudentListXml();
        System.out.println("XmlExporterTest passed successfully!");
    }

    private static void testSingleStudentXml() {
        Student s = new Student(1, "R-101", "John Doe", "john@example.com", Course.JAVA, 88.5);
        String xml = XmlExporter.toXml(s);
        assert xml.contains("<id>1</id>");
        assert xml.contains("<name>John Doe</name>");
        assert xml.contains("<marks>88.5</marks>");
    }

    private static void testStudentListXml() {
        Student s1 = new Student(1, "R-101", "John", "j@ex.com", Course.JAVA, 80.0);
        Student s2 = new Student(2, "R-102", "Jane", "j2@ex.com", Course.PYTHON, 90.0);
        String xml = XmlExporter.toXml(Arrays.asList(s1, s2));
        assert xml.contains("<students>");
        assert xml.contains("<name>John</name>");
        assert xml.contains("<name>Jane</name>");
        assert xml.contains("</students>");
    }
}
