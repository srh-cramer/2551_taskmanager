package de.taskmanager.command;

public interface Command {
    void execute();
    void undo();
}
