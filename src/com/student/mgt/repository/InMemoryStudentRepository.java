package com.student.mgt.repository;

import com.student.mgt.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryStudentRepository implements StudentRepository {
    private final Map<String, Student> studentMap = new ConcurrentHashMap<>();

    @Override
    public void save(Student student) {
        if (student != null && student.getStudentId() != null) {
            studentMap.put(student.getStudentId(), student);
        }
    }

    @Override
    public Optional<Student> findById(String id) {
        if (id == null) return Optional.empty();
        return Optional.ofNullable(studentMap.get(id));
    }

    @Override
    public Optional<Student> findByRollNo(String rollNo) {
        if (rollNo == null) return Optional.empty();
        return studentMap.values().stream()
                .filter(s -> rollNo.equalsIgnoreCase(s.getRollNo()))
                .findFirst();
    }

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(studentMap.values());
    }

    @Override
    public boolean deleteById(String id) {
        if (id == null) return false;
        return studentMap.remove(id) != null;
    }

    @Override
    public void update(Student student) {
        if (student != null && student.getStudentId() != null && studentMap.containsKey(student.getStudentId())) {
            studentMap.put(student.getStudentId(), student);
        }
    }

    public void clear() {
        studentMap.clear();
    }
}
