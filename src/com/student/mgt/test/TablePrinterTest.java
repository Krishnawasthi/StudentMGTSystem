package com.student.mgt.test;

import com.student.mgt.model.Course;
import com.student.mgt.model.Student;
import com.student.mgt.util.TablePrinter;

import java.util.ArrayList;
import java.util.List;

public class TablePrinterTest {
    public static void main(String[] args) {
        System.out.println("Running TablePrinter Unit Tests...");

        List<Student> list = new ArrayList<>();
        Student s1 = new Student("ID01", "CS101", "Grace Hopper", "grace@example.com", Course.COMPUTER_SCIENCE);
        s1.getMarks().add(98.0);
        list.add(s1);

        try {
            System.out.println("Testing Empty Table Printing:");
            TablePrinter.printStudentTable(new ArrayList<>());

            System.out.println("Testing Populated Table Printing:");
            TablePrinter.printStudentTable(list);

            System.out.println("[PASS] TablePrinter executed without exceptions");
            System.out.println("All TablePrinter unit tests passed!");
        } catch (Exception e) {
            System.err.println("[FAIL] TablePrinter test failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
