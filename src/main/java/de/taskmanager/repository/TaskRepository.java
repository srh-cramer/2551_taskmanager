package de.taskmanager.repository;

import de.taskmanager.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

    private final List<Task> tasks = new ArrayList<>();

    public void save(Task task) {
        tasks.add(task);
    }

    public List<Task> findAll() {
        // Gib eine Kopie der Liste zurück, damit eigentliche Liste nicht verändert werden kann
        return new ArrayList<>(tasks);
    }

    public boolean deleteById(int id) {
        return tasks.removeIf(task -> task.getId() == id);
    }
}
