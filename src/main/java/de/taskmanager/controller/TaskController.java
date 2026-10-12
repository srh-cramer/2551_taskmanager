package de.taskmanager.controller;

import de.taskmanager.model.Priority;
import de.taskmanager.model.Task;
import de.taskmanager.model.TaskStatus;
import de.taskmanager.service.TaskService;
import de.taskmanager.view.ConsoleView;

import java.util.List;

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
                changeTaskStatus();
            } else if (input.equals("3")) {
                showAllTasks();
//            } else if (input.equals("4")) {
//                showTasksSortedByPriority();
//            } else if (input.equals("5")){
//                findTaskById();
//            } else if (input.equals("6")){
//                showTasksByStatus();
//            } else if (input.equals("7")){
//                showStatistics();
//            } else if (input.equals("8")){
//                undoLastAction();
            }else if (input.equals("0")) {
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
        service.createTask(title, description, priority);
    }

    public void showAllTasks() {
        List<Task> tasks = service.getAllTasks();
        view.showTasks(tasks);
    }

    public void changeTaskStatus() {
        int taskId = view.readTaskId();
        TaskStatus status = view.readTaskStatus();
        // TODO: Aufgabe 1
        view.showMessage("Statusänderung ausgeführt.");

    }
}
