package de.taskmanager.controller;

import de.taskmanager.command.AddTaskCommand;
import de.taskmanager.command.ChangeTaskStatusCommand;
import de.taskmanager.command.Command;
import de.taskmanager.command.CommandInvoker;
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
    private final CommandInvoker invoker;

    public TaskController(TaskService service, ConsoleView view, CommandInvoker invoker) {
        this.service = service;
        this.view = view;
        this.invoker = invoker;
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
            } else if (input.equals("4")) {
                showTasksSortedByPriority();
            } else if (input.equals("5")){
                findTaskById();
            } else if (input.equals("6")){
                showTasksByStatus();
            } else if (input.equals("7")){
                showStatistics();
            } else if (input.equals("8")){
                undoLastAction();
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
        //service.createTask(title, description, priority);
        Command createTaskCommand =
                new AddTaskCommand(service, title, description, priority);
        invoker.execute(createTaskCommand);
    }

    public void showAllTasks() {
        List<Task> tasks = service.getAllTasks();
        view.showTasks(tasks);
    }

    public void changeTaskStatus() {
        int taskId = view.readTaskId();
        TaskStatus status = view.readTaskStatus();
        //service.changeTaskStatus(taskId, status);
        Command changeTaskStatusCommand =
                new ChangeTaskStatusCommand(service, taskId, status);
        invoker.execute(changeTaskStatusCommand);
        view.showMessage("Statusänderung ausgeführt.");
    }

    public void showTasksSortedByPriority(){
        List<Task> tasks = service.getTasksSortedByPriority();
        view.showTasks(tasks);
    }

    public void findTaskById(){
        int id = view.readTaskId();
        Optional<Task> task = service.findTaskById(id);
        if (task.isPresent()){
            view.showTask(task.get());
        } else {
            view.showMessage("Keine Aufgabe mit dieser ID gefunden");
        }
    }

    public void showTasksByStatus(){
        TaskStatus status = view.readTaskStatus();
        List<Task> tasks = service.getTasksByStatus(status);
        view.showTasks(tasks);
    }

    public void showStatistics(){
        // Rufe dreimal Methode service.countTasksByStatus auf
        // Einmal pro Status
        int countOpen = service.countTasksByStatus(TaskStatus.OPEN);
        int countInProgress = service.countTasksByStatus(TaskStatus.IN_PROGRESS);
        int countDone = service.countTasksByStatus(TaskStatus.DONE);
        //int countTotal = countOpen + countInProgress + countDone;
        int countTotal = service.getAllTasks().size();
        // Gib Ergebnisse an View weiter
        view.showStatistics(countTotal, countOpen, countInProgress, countDone);

    }

    public void undoLastAction(){
        boolean success = invoker.undo();
        if (success){
            view.showMessage("Letzte Aktion rückgängig gemacht");
        } else {
            view.showMessage("Keine Aktion zum Rückgängig machen vorhanden.");
        }
    }
}
