package com.student.mgt.util;

import com.student.mgt.model.Course;
import com.student.mgt.model.Student;
import com.student.mgt.service.AttendanceService;
import com.student.mgt.service.ReportService;
import com.student.mgt.service.StudentService;

import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private final StudentService studentService;
    private final AttendanceService attendanceService;
    private final ReportService reportService;
    private final Scanner scanner;

    public ConsoleUI(StudentService studentService, AttendanceService attendanceService, ReportService reportService) {
        this.studentService = studentService;
        this.attendanceService = attendanceService;
        this.reportService = reportService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1": addStudent(); break;
                case "2": listStudents(); break;
                case "3": addMarks(); break;
                case "4": updateAttendance(); break;
                case "5": reportService.printClassSummaryReport(); break;
                case "6": exportCsv(); break;
                case "7": deleteStudent(); break;
                case "0":
                    running = false;
                    System.out.println("Exiting application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n--- STUDENT MANAGEMENT SYSTEM ---");
        System.out.println("1. Register Student");
        System.out.println("2. View All Students");
        System.out.println("3. Add Marks to Student");
        System.out.println("4. Update Attendance");
        System.out.println("5. Print Class Summary Report");
        System.out.println("6. Export Students to CSV");
        System.out.println("7. Delete Student");
        System.out.println("0. Exit");
    }

    private void addStudent() {
        try {
            System.out.print("Enter Roll Number: ");
            String roll = scanner.nextLine();
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Email: ");
            String email = scanner.nextLine();
            System.out.println("Select Course (1: CS, 2: IT, 3: EE, 4: ME, 5: CE): ");
            int c = Integer.parseInt(scanner.nextLine());
            Course course = Course.values()[c - 1];

            Student s = studentService.registerStudent(roll, name, email, course);
            System.out.println("Successfully registered: " + s);
        } catch (Exception e) {
            System.out.println("Error registering student: " + e.getMessage());
        }
    }

    private void listStudents() {
        List<Student> students = studentService.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students registered.");
            return;
        }
        students.forEach(System::println);
    }

    private void addMarks() {
        try {
            System.out.print("Enter Student Roll Number: ");
            String roll = scanner.nextLine();
            Student s = studentService.getStudentByRollNo(roll);
            System.out.print("Enter Marks (0-100): ");
            double marks = Double.parseDouble(scanner.nextLine());
            studentService.addMarks(s.getStudentId(), marks);
            System.out.println("Marks added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding marks: " + e.getMessage());
        }
    }

    private void updateAttendance() {
        try {
            System.out.print("Enter Student Roll Number: ");
            String roll = scanner.nextLine();
            Student s = studentService.getStudentByRollNo(roll);
            System.out.print("Enter Attendance Percentage (0-100): ");
            double att = Double.parseDouble(scanner.nextLine());
            attendanceService.updateAttendance(s.getStudentId(), att);
            System.out.println("Attendance updated.");
        } catch (Exception e) {
            System.out.println("Error updating attendance: " + e.getMessage());
        }
    }

    private void exportCsv() {
        try {
            CsvExporter.exportStudentsToCsv(studentService.getAllStudents(), "students_export.csv");
            System.out.println("Successfully exported data to students_export.csv");
        } catch (Exception e) {
            System.out.println("Export failed: " + e.getMessage());
        }
    }

    private void deleteStudent() {
        try {
            System.out.print("Enter Student Roll Number to Delete: ");
            String roll = scanner.nextLine();
            Student s = studentService.getStudentByRollNo(roll);
            studentService.deleteStudent(s.getStudentId());
            System.out.println("Student record deleted.");
        } catch (Exception e) {
            System.out.println("Delete failed: " + e.getMessage());
        }
    }
}
