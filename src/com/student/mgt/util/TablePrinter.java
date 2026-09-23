package com.student.mgt.util;

import com.student.mgt.model.Student;

import java.util.List;

public class TablePrinter {

    public static void printStudentTable(List<Student> students) {
        if (students == null || students.isEmpty()) {
            System.out.println("+------------------------------------------------------------------------------------------------+");
            System.out.println("| No records available.                                                                          |");
            System.out.println("+------------------------------------------------------------------------------------------------+");
            return;
        }

        String border = "+----------+------------+----------------------+--------------------------+-----------------------+--------+------------+";
        String header = "| ID       | Roll No    | Name                 | Email                    | Course                | GPA    | Status     |";

        System.out.println(border);
        System.out.println(header);
        System.out.println(border);

        for (Student s : students) {
            System.out.printf("| %-8s | %-10s | %-20s | %-24s | %-21s | %-6.2f | %-10s |%n",
                    truncate(s.getStudentId(), 8),
                    truncate(s.getRollNo(), 10),
                    truncate(s.getName(), 20),
                    truncate(s.getEmail(), 24),
                    truncate(s.getCourse().getDisplayName(), 21),
                    s.getGpa(),
                    truncate(s.getStatus().getDisplayName(), 10));
        }
        System.out.println(border);
    }

    private static String truncate(String text, int width) {
        if (text == null) return "";
        if (text.length() <= width) return text;
        return text.substring(0, width - 3) + "...";
    }
}
