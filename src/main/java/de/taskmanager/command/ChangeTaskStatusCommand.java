package de.taskmanager.command;

import de.taskmanager.model.Task;
import de.taskmanager.model.TaskStatus;
import de.taskmanager.service.TaskService;

import java.util.Optional;

public class ChangeTaskStatusCommand implements Command{

    private final TaskService service;
    private final int taskId;
    private final TaskStatus status;

    private TaskStatus previousStatus;
    private boolean executedSuccessfully;

    public ChangeTaskStatusCommand(
            TaskService service,
            int taskId,
            TaskStatus status) {
        this.service = service;
        this.taskId = taskId;
        this.status = status;
    }

    @Override
    public void execute() {
        executedSuccessfully = false;

        Optional<Task> taskOptional = service.findTaskById(taskId);
        if (taskOptional.isEmpty()) {
            return;
        }

        Task task = taskOptional.get();
        previousStatus = task.getStatus();

        executedSuccessfully = service.changeTaskStatus(taskId, status);
    }

    @Override
    public void undo(){
        if (previousStatus != null){
            service.changeTaskStatus(taskId, previousStatus);
            executedSuccessfully = false;
        }
    }
}
