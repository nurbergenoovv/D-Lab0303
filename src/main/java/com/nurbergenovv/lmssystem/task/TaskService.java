package com.nurbergenovv.lmssystem.task;

import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskService {
    private final List<Task> store = new ArrayList<>();
    private final AtomicLong seq = new AtomicLong(1);

    public List<Task> findAll() { return store; }
    public Optional<Task> findById(Long id) { return store.stream().filter(t -> t.getId().equals(id)).findFirst(); }

    public Task create(String name, String deadlineDate, boolean isCompleted) {
        Task t = new Task(seq.getAndIncrement(), name, deadlineDate, isCompleted);
        store.add(t);
        return t;
    }

    public boolean update(Long id, String name, String deadlineDate, boolean isCompleted) {
        return findById(id).map(t -> {
            t.setName(name);
            t.setDeadlineDate(deadlineDate);
            t.setCompleted(isCompleted);
            return true;
        }).orElse(false);
    }

    public void delete(Long id) {
        store.removeIf(t -> t.getId().equals(id));
    }
}