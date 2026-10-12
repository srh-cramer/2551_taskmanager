package de.taskmanager.command;

import java.util.ArrayDeque;
import java.util.Deque;

public class CommandInvoker {

    private final Deque<Command> history = new ArrayDeque<>();

    public void execute(Command command) {
        command.execute();
        history.push(command);
    }

    public boolean undo() {
        if (history.isEmpty()) {
            return false;
        }

        Command command = history.pop();
        command.undo();
        return true;
    }
}
