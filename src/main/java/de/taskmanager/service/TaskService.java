package de.taskmanager.service;

import de.taskmanager.model.Priority;
import de.taskmanager.model.Task;
import de.taskmanager.model.TaskStatus;
import de.taskmanager.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

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

    public Optional<Task> findTaskById(int id) {
        return repository.findById(id);
    }

    // Task-Status ändern
    // Gibt true bei Erfolg zurück, false wenn Task-ID nicht existiert
    public boolean changeTaskStatus(int id, TaskStatus status) {
        Optional<Task> task = repository.findById(id);

        if (task.isEmpty()) {
            return false;
        }

        task.get().setStatus(status);
        return true;
    }
}
