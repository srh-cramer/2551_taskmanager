package de.taskmanager.command;

import de.taskmanager.model.Priority;
import de.taskmanager.model.Task;
import de.taskmanager.service.TaskService;

public class AddTaskCommand implements Command {
    private final TaskService service;
    private final String title;
    private final String description;
    private final Priority priority;

    private Task createdTask;

    public AddTaskCommand(
            TaskService service,
            String title,
            String description,
            Priority priority) {
        this.service = service;
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    @Override
    public void execute() {
        createdTask = service.createTask(title, description, priority);
    }

    @Override
    public void undo() {
        if (createdTask != null) {
            service.deleteTaskById(createdTask.getId());
            createdTask = null;
        }
    }
}
