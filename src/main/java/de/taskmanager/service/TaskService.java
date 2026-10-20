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

    public boolean deleteTaskById(int id) {
        return repository.deleteById(id);
    }

    public Optional<Task> findTaskById(int id){
        return repository.findById(id);
    }

    public boolean changeTaskStatus(int id, TaskStatus status){
        Optional<Task> task = repository.findById(id);
        if (task.isPresent()){
            task.get().setStatus(status);
            return true;
        }
        return false;
    }

    public List<Task> getTasksSortedByPriority(){
        List<Task> tasks = repository.findAll();
        return tasks.stream()
                .sorted((task1, task2) ->
                        task2.getPriority().compareTo(task1.getPriority()))
                .toList();
    }

    public List<Task> getTasksByStatus(TaskStatus status) {
        // stream() erzeugt Stream aus Liste
        // filter() lässt nur Elemente mit passendem Status durch
        // toList() sammelt passende Elemente in einer Liste
        return repository.findAll().stream()
                .filter(task -> task.getStatus() == status)
                .toList();
    }

}
