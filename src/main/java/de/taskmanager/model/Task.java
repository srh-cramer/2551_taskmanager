
package de.taskmanager.model;

public class Task {

    private final int id;
    private final String title;
    private final String description;
    private final Priority priority;

    private TaskStatus status;

    public Task(int id, String title, String description, Priority priority) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Der Titel darf nicht leer sein.");
        }

        if (priority == null){
            throw new IllegalArgumentException("Priorität darf nicht null sein");
        }

        this.id = id;
        this.title = title;
        this.priority = priority;
        this.description = description;
        this.status = TaskStatus.OPEN;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Priority getPriority() {
        return priority;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        if (status == null){
            throw new IllegalArgumentException("Status darf nicht null sein");
        }
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + "'" +
                ", priority=" + priority +
                ", status=" + status +
                '}';
    }
}
