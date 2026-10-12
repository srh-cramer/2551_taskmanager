package de.taskmanager.controller;

import de.taskmanager.model.Priority;
import de.taskmanager.model.Task;
import de.taskmanager.model.TaskStatus;
import de.taskmanager.service.TaskService;
import de.taskmanager.view.ConsoleView;

import java.util.List;
import java.util.Optional;

public class TaskController {

    private final TaskService service;
    private final ConsoleView view;

    public TaskController(TaskService service, ConsoleView view) {
        this.service = service;
        this.view = view;
    }

    public void run() {
        boolean running = true;

        while (running) {
            view.showMenu();
            String input = view.readMenuChoice();

            if (input.equals("1")) {
                createTask();
            } else if (input.equals("2")) {
                showAllTasks();
            } else if (input.equals("3")) {
                changeTaskStatus();
            } else if (input.equals("4")) {
                findTaskById();
            } else if (input.equals("0")) {
                running = false;
            } else {
                view.showMessage("Ungültige Auswahl.");
            }
        }

        view.showMessage("Task Manager beendet.");
    }

    public void createTask() {
        String title = view.readTaskTitle();
        String description = view.readTaskDescription();
        Priority priority = view.readPriority();

        Task task = service.createTask(title, description, priority);

        view.showMessage("Aufgabe wurde erstellt: " + task.getTitle());
    }

    public void showAllTasks() {
        List<Task> tasks = service.getAllTasks();
        view.showTasks(tasks);
    }

    public void changeTaskStatus() {
        int id = view.readTaskId();
        TaskStatus status = view.readTaskStatus();

        boolean changed = service.changeTaskStatus(id, status);

        if (changed) {
            view.showMessage("Status wurde geändert.");
        } else {
            view.showMessage("Keine Aufgabe mit dieser ID gefunden.");
        }
    }

    public void findTaskById() {
        int id = view.readTaskId();

        Optional<Task> task = service.findTaskById(id);

        if (task.isPresent()) {
            view.showTask(task.get());
        } else {
            view.showMessage("Keine Aufgabe mit dieser ID gefunden.");
        }
    }
}
