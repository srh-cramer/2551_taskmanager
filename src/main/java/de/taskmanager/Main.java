package de.taskmanager;

import de.taskmanager.command.CommandInvoker;
import de.taskmanager.controller.TaskController;
import de.taskmanager.repository.TaskRepository;
import de.taskmanager.service.TaskService;
import de.taskmanager.view.ConsoleView;

public class Main {

    public static void main(String[] args) {
        TaskRepository repository = new TaskRepository();
        TaskService service = new TaskService(repository);
        ConsoleView view = new ConsoleView();
        CommandInvoker invoker = new CommandInvoker();

        TaskController controller = new TaskController(service, view, invoker);

        controller.run();
    }
}
