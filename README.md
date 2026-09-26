# Student Management System (Core Java)

A standalone, object-oriented **Core Java** application for managing student records, attendance, grades, and academic reports.

## System Features
- Core Java Object-Oriented Architecture (Encapsulation, Inheritance, Polymorphism, Abstraction)
- Flexible Data Persistence (File-based Storage & In-Memory Repositories)
- Student Status (Active, Suspended, Graduated, Dropped Out, On Leave, Expelled) & Semester Management
- Student Profiles with Date of Birth and Enrollment Date tracking
- Teacher Management & Course Assignments with Delete, Update, and Search capabilities
- Extended Course Catalog (7 engineering disciplines including Electronics & Chemical Engineering)
- Course Lookup by Code and Display Name
- Advanced Grade & GPA 4.0 Calculation Engine with Honors Distinction
- Comprehensive Reporting (Highest/Lowest Scorer, Grade Distribution, Status Counts, Class GPA)
- Batch Attendance Tracking & Low-Attendance Alerts
- Data Export Support (CSV & JSON Formats)
- System Data Backup Utility (`BackupUtil`)
- Formatted ASCII Table Output Rendering (`TablePrinter`)
- String & Date Utility Libraries (`StringUtil`, `DateUtil`)
- Integrated File Logging with DEBUG/INFO/WARN/ERROR levels (`student_mgt.log`)
- Robust Custom Exception Hierarchy (including Teacher exceptions)
- Unit Test Suites (`StudentServiceTest`, `ValidationUtilTest`, `StudentServiceSearchTest`, `StudentStatusTest`, `TeacherServiceTest`, `TablePrinterTest`)
- Interactive Console UI

## Prerequisites
- JDK 8 or higher (Tested with JDK 22)

## How to Run & Test
Compile and run the main application using `run.bat`:
```bash
run.bat
```

To run unit tests:
```bash
run_tests.bat
```
