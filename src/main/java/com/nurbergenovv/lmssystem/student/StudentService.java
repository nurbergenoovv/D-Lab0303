package com.nurbergenovv.lmssystem.student;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class StudentService {
    private final List<Student> store = new ArrayList<>();
    private final AtomicLong seq = new AtomicLong(1);

    public List<Student> findAll() {
        return store;
    }

    public void add(String name, String surname, int exam) {
        Long id = seq.getAndIncrement();
        Student s = new Student();
        s.setId(id);
        s.setName(name);
        s.setSurname(surname);
        s.setExam(exam);
        s.setMark(grade(exam));
        store.add(s);
    }

    private String grade(int exam) {
        if (exam >= 90) return "A";
        if (exam >= 75) return "B";
        if (exam >= 60) return "C";
        if (exam >= 50) return "D";
        return "F";
    }
}