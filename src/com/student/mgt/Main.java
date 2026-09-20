package com.student.mgt;

import com.student.mgt.config.AppConfig;
import com.student.mgt.model.Course;
import com.student.mgt.repository.FileStudentRepository;
import com.student.mgt.repository.StudentRepository;
import com.student.mgt.service.*;
import com.student.mgt.util.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting " + AppConfig.APP_NAME + " v" + AppConfig.VERSION);

        StudentRepository repository = new FileStudentRepository(AppConfig.DATA_FILE_PATH);
        StudentService studentService = new StudentServiceImpl(repository);
        AttendanceService attendanceService = new AttendanceService(repository);
        ReportService reportService = new ReportService(repository);

        // Seed initial sample data if empty
        if (studentService.getAllStudents().isEmpty()) {
            try {
                studentService.registerStudent("CS101", "Alice Smith", "alice@example.com", Course.COMPUTER_SCIENCE);
                studentService.registerStudent("IT102", "Bob Johnson", "bob@example.com", Course.INFORMATION_TECHNOLOGY);
                studentService.registerStudent("EE103", "Charlie Brown", "charlie@example.com", Course.ELECTRICAL_ENGINEERING);
            } catch (Exception ignored) {}
        }

        ConsoleUI ui = new ConsoleUI(studentService, attendanceService, reportService);
        ui.start();
    }
}
