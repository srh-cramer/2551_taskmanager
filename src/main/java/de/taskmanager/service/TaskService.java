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

    public boolean deleteTaskById(int id) {
        return repository.deleteById(id);
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

    public List<Task> getTasksByStatus(TaskStatus status) {
        // stream() erzeugt Stream aus Liste
        // filter() lässt nur Elemente mit passendem Status durch
        // toList() sammelt passende Elemente in einer Liste
        return repository.findAll().stream()
                .filter(task -> task.getStatus() == status)
                .toList();
    }

    public List<Task> getTasksSortedByPriority() {
        // Achtug: sorted() sortiert in der Reihenfolge, in der die Enum-Werte deklariert wurden!
        // Daher drehen wir die Sortierreihenfolge um
        return repository.findAll().stream()
                .sorted((task1, task2) ->
                        task2.getPriority().compareTo(task1.getPriority()))
                .toList();
    }


    public int countAllTasks() {
        // Anzahl ohne Filterung mit size():
        return repository.findAll().size();
    }

    public long countTasksByStatus(TaskStatus status) {
        // Anzahl mit Filterung mit stream(), filter() und count():
        return repository.findAll().stream()
                .filter(task -> task.getStatus() == status)
                .count();
    }
}
