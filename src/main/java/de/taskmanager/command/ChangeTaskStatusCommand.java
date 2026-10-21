package de.taskmanager.command;

import de.taskmanager.model.Task;
import de.taskmanager.model.TaskStatus;
import de.taskmanager.service.TaskService;

import java.util.Optional;


public class ChangeTaskStatusCommand implements Command{

    private final TaskStatus status;
    private final int id;
    private final TaskService service;

    private TaskStatus previousStatus;

    public ChangeTaskStatusCommand(TaskService service,
                                   int id,
                                   TaskStatus status){
        this.service = service;
        this.id = id;
        this.status = status;
    }

    public void execute(){
        Optional<Task> task = service.findTaskById(id);
        if (task.isEmpty()){
            return;
        }

        this.previousStatus = task.get().getStatus();
        service.changeTaskStatus(id, status);
    }

    public void undo(){
        if (previousStatus != null) {
            service.changeTaskStatus(id, previousStatus);
        }
    }
}
