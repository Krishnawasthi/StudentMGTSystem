package com.student.mgt.repository;

import com.student.mgt.model.Course;
import com.student.mgt.model.Student;

import java.io.*;
import java.util.*;

public class FileStudentRepository implements StudentRepository {
    private final String filePath;
    private final Map<String, Student> studentMap = new HashMap<>();

    public FileStudentRepository(String filePath) {
        this.filePath = filePath;
        loadFromFile();
    }

    @Override
    public void save(Student student) {
        studentMap.put(student.getStudentId(), student);
        saveToFile();
    }

    @Override
    public Optional<Student> findById(String id) {
        return Optional.ofNullable(studentMap.get(id));
    }

    @Override
    public Optional<Student> findByRollNo(String rollNo) {
        return studentMap.values().stream()
                .filter(s -> s.getRollNo().equalsIgnoreCase(rollNo))
                .findFirst();
    }

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(studentMap.values());
    }

    @Override
    public boolean deleteById(String id) {
        Student removed = studentMap.remove(id);
        if (removed != null) {
            saveToFile();
            return true;
        }
        return false;
    }

    @Override
    public void update(Student student) {
        save(student);
    }

    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Student s : studentMap.values()) {
                StringBuilder marksStr = new StringBuilder();
                for (double m : s.getMarks()) {
                    marksStr.append(m).append(";");
                }
                writer.write(String.format("%s,%s,%s,%s,%s,%s,%.2f%n",
                        s.getStudentId(), s.getRollNo(), s.getName(), s.getEmail(),
                        s.getCourse().name(), marksStr.toString(), s.getAttendancePercentage()));
            }
        } catch (IOException e) {
            System.err.println("Error saving student data to file: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        File file = new File(filePath);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length >= 7) {
                    String id = parts[0];
                    String roll = parts[1];
                    String name = parts[2];
                    String email = parts[3];
                    Course course = Course.valueOf(parts[4]);
                    Student student = new Student(id, roll, name, email, course);
                    
                    if (!parts[5].isEmpty()) {
                        for (String m : parts[5].split(";")) {
                            if (!m.isEmpty()) student.getMarks().add(Double.parseDouble(m));
                        }
                    }
                    student.setAttendancePercentage(Double.parseDouble(parts[6]));
                    studentMap.put(id, student);
                }
            }
        } catch (Exception e) {
            System.err.println("Error loading student data: " + e.getMessage());
        }
    }
}
