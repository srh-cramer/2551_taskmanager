package de.taskmanager.service;

import de.taskmanager.model.Priority;
import de.taskmanager.model.Task;
import de.taskmanager.repository.TaskRepository;

import java.util.List;

public class TaskService {

    private final TaskRepository repository;
    // Mock-Lösung für ID Auto-Increment ohne DB:
    private int nextId = 1;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Task createTask(String title, String description, Priority priority) {
        Task task = new Task(nextId, title, description, priority);

        repository.save(task);
        nextId++;

        return task;
    }

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public boolean deleteTaskById(int id) {
        return repository.deleteById(id);
    }

}
