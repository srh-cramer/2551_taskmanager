package de.taskmanager;

import de.taskmanager.controller.TaskController;
import de.taskmanager.repository.TaskRepository;
import de.taskmanager.service.TaskService;
import de.taskmanager.view.ConsoleView;

public class Main {

    public static void main(String[] args) {
        TaskRepository repository = new TaskRepository();
        TaskService service = new TaskService(repository);
        ConsoleView view = new ConsoleView();
        TaskController controller = new TaskController(service, view);

        controller.run();
    }
}
