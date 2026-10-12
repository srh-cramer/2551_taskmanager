package de.taskmanager.view;

import de.taskmanager.model.Priority;
import de.taskmanager.model.Task;
import de.taskmanager.model.TaskStatus;

import java.util.List;
import java.util.Scanner;

public class ConsoleView {

    private final Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        System.out.println();
        System.out.println("=== Task Manager ===");
        System.out.println("1 - Aufgabe erstellen");
        System.out.println("2 - Aufgabenstatus ändern");
        System.out.println("3 - Alle Aufgaben anzeigen");
//        System.out.println("4 - Aufgaben nach Priorität sortiert anzeigen");
//        System.out.println("5 - Aufgaben filtern nach: ID");
//        System.out.println("6 - Aufgaben filtern nach: Status");
//        System.out.println("7 - Statistiken anzeigen");
//        System.out.println("8 - Letzte Änderung rückgängig machen");
        System.out.println("0 - Beenden");
    }

    public String readMenuChoice() {
        System.out.print("Auswahl: ");
        return scanner.nextLine();
    }

    public String readTaskTitle() {
        System.out.print("Titel: ");
        return scanner.nextLine();
    }

    public String readTaskDescription() {
        System.out.print("Beschreibung: ");
        return scanner.nextLine();
    }

    public Priority readPriority() {
        while (true) {
            System.out.println("Priorität auswählen:");
            System.out.println("1 - LOW");
            System.out.println("2 - MEDIUM");
            System.out.println("3 - HIGH");
            System.out.print("Auswahl: ");

            String userInput = scanner.nextLine().strip();

            if (userInput.equals("1")){
                return Priority.LOW;
            }
            else if (userInput.equals("2")){
                return Priority.MEDIUM;
            }
            else if (userInput.equals("3")){
                return Priority.HIGH;
            }
            else {
                System.out.println("Ungültige Auswahl.");
            }
        }
    }

    public int readTaskId() {
        while (true) {
            System.out.print("Aufgaben-ID: ");
            String input = scanner.nextLine();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Bitte eine gültige Zahl eingeben.");
            }
        }
    }

    public TaskStatus readTaskStatus() {
        while (true) {
            System.out.println("Status auswählen:");
            System.out.println("1 - OPEN");
            System.out.println("2 - IN_PROGRESS");
            System.out.println("3 - DONE");
            System.out.print("Auswahl: ");

            String userInput = scanner.nextLine().strip();

            if (userInput.equals("1")){
                return TaskStatus.OPEN;
            }
            else if (userInput.equals("2")){
                return TaskStatus.IN_PROGRESS;
            }
            else if (userInput.equals("3")){
                return TaskStatus.DONE;
            }
            else {
                System.out.println("Ungültige Auswahl.");
            }
        }
    }

    public void showTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Keine Aufgaben vorhanden.");
            return;
        }

        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    public void showTask(Task task) {
        System.out.println(task);
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
