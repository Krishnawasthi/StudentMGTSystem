package com.student.mgt.repository;

import com.student.mgt.model.Teacher;

import java.io.*;
import java.util.*;

public class FileTeacherRepository implements TeacherRepository {
    private final String filePath;

    public FileTeacherRepository(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public synchronized void save(Teacher teacher) {
        Map<String, Teacher> teachers = loadAll();
        teachers.put(teacher.getTeacherId(), teacher);
        saveAll(teachers);
    }

    @Override
    public synchronized Optional<Teacher> findById(String id) {
        if (id == null) return Optional.empty();
        return Optional.ofNullable(loadAll().get(id));
    }

    @Override
    public synchronized List<Teacher> findAll() {
        return new ArrayList<>(loadAll().values());
    }

    @Override
    public synchronized boolean deleteById(String id) {
        Map<String, Teacher> teachers = loadAll();
        if (teachers.remove(id) != null) {
            saveAll(teachers);
            return true;
        }
        return false;
    }

    @Override
    public synchronized void update(Teacher teacher) {
        save(teacher);
    }

    @SuppressWarnings("unchecked")
    private Map<String, Teacher> loadAll() {
        File file = new File(filePath);
        if (!file.exists() || file.length() == 0) {
            return new HashMap<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (Map<String, Teacher>) ois.readObject();
        } catch (Exception e) {
            return new HashMap<>();
        }
    }

    private void saveAll(Map<String, Teacher> teachers) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(teachers);
        } catch (IOException ignored) {}
    }
}
