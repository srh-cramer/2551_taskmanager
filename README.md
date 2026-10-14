# TaskManager

**TaskManager** ist ein schlankes kleines Kommandozeilen-Tool zur Aufgabenverwaltung.

In diesem Projekt entwickeln Sie ein Kommandozeilen-Tool zur Aufgabenverwaltung weiter. 
Bisher lassen sich neue Aufgaben anlegen sowie alle existierenden Aufgaben anzeigen.

Sie werden in diesem Projekt folgende Konzepte kennenlernen:

* Optionals
* Streams
* Design-Pattern: Command

## Ausgangssituation
Das Tool ist rein kommandozeilenbasiert. Das Tool kann bereits:
* Ein Hauptmenü anzeigen
* Eingaben durch den Benutzer verarbeiten
* Einen Benutzer durch das Anlegen einer Aufgabe leiten:
  * Titel festlegen
  * Beschreibung festlegen
  * Priorität festlegen (Niedrig, Mittel, Hoch)
  * Die Aufgabe mit Status OPEN anlegen
* Alle angelegten Aufgaben anzeigen

## Zielbild
Folgende weitere Funktionen sollen am Ende dieses Projektes vorhanden sein:
* Aufgabenstatus ändern (diese Option wird bereits angezeigt, ist jedoch noch nicht umgesetzt)
* Aufgaben nach Priorität sortiert anzeigen
* Aufgaben filtern nach:
  * ID
  * Status
* Aufgaben-Statistiken anzeigen
* Letzte Änderung rückgängig machen
  * Eine Änderung ist das Anlegen einer Aufgabe oder ihre Statusänderung
---

## Aufgabe 1 - Status ändern
Im Hauptmenü wird bereits die Option "Aufgabenstatus ändern" angezeigt. 
Die Option kann aufgerufen werden und führt den Benutzer durch die Änderung, aber die Änderung wird am Ende nicht durchgeführt.

Ihre Aufgabe ist es, dass die gewünschte Änderung auch tatsächlich durchgeführt wird.

### Anforderungen
* Machen Sie sich zunächst mit dem vorgegebenen Code vertraut. Folgende Fragen sollten Sie beantworten können:
  * Wie wird die Benutzereingabe auf der Konsole verarbeitet?
  * Wo müssten Sie ansetzen, um eine weitere Aufgabenpriorität (z.B. Extrahoch) anzulegen?
  * Werden irgendwo die angelegten Aufgaben dauerhaft gespeichert? Wenn ja, wo?
  * In welcher Datei müssen sie ansetzen, um diese Aufgabe umzusetzen?
* Ergänzen Sie in `TaskService.java` eine Methode mit folgendem Methodenkopf: `public boolean changeTaskStatus(int id, TaskStatus status)`
* Der Rückgabewert der Methode soll `true` sein, wenn der Status erfolgreich geändert wurde, andernfalls `false`
  * Überlegen Sie sich hierzu: In welchem Fall lässt sich der Status nicht ändern?
* Rufen Sie die Methode anschließend in der Klasse `TaskController` an der passenden Stelle auf
* Nach diesen Änderungen soll der Benutzer den Status einer Aufgabe erfolgreich ändern können.

### Tipps
* Folgende Methoden helfen Ihnen, um Ihr Ziel zu erreichen: 
  * `findById()` in der Klasse `TaskRepository`
  * `setStatus()` in der Klasse `Task`
* Wenn die Aufgabe mit der angegebenen ID nicht existiert, lässt sich ihr Status auch nicht ändern.
Überprüfen Sie also zunächst, ob die Aufgabe tatsächlich existiert und ändern sie nur dann ihren Status.
* Die Stelle, wo Sie Ihre fertige Methode aufrufen sollen, ist im Controller mit einem TODO markiert.
---

## Aufgabe 2 - Status ändern mit Optionals
Ihr Ergebnis aus Aufgabe 1 funktioniert, kann aber einfacher mit **Optionals** gelöst werden.
Ein `Optional` ist ein bestimmter Datentyp, der verwendet werden kann, wenn ein Objekt eventuell existiert oder nicht.

Der klassische Anwendungsfall für Optionals sind Datenbankabfragen, insbesondere Lookups per ID.

Sie können den Datentyp `Optional` verwenden, indem Sie ihn importieren und dann ähnlich wie eine Liste deklarieren:
```java
import java.util.Optional;

Optional<String> stringWithId1 = repository.findById(1);
```
In diesem Fall ist die Variable `stringWithId1` ein **String-Optional**: D.h. wenn ein Eintrag mit der ID 1 existiert, "existiert" auch der String.

### Anforderungen
Überschreiben Sie zunächst die Implementierung von `findById()` in der Klasse `TaskRepository` mit dieser Umsetzung:
```java
public Optional<Task> findById(int id) {
    return tasks.stream()
            .filter(task -> task.getId() == id)
            .findFirst();
    }
```
Die Syntax dieser Methode vollziehen Sie in der nächsten Aufgabe nach.
Beachten Sie zunächst nur den Rückgabetyp der veränderten Methode. 

Passen Sie Ihre Umsetzung von `changeTaskStatus()` an diesen neuen Rückgabetypen an:
* Die Rückgabe von `changeTaskStatus()` soll sich nicht ändern, d.h. weiterhin `true` bei einer erfolgreichen Änderung, andernfalls `false`
* Überlegen Sie, wie Sie mit dem Datentyp `Optional<Task>` arbeiten können.
Optionals verfügen über viele Methoden, um mit ihnen zu arbeiten. 
Lassen Sie sich von IntelliJ beim Umgang mit Optionals helfen.
* Ihre finale Umsetzung soll nichts am Verhalten des Tools ändern.

### Tipps
* Sie benötigen Optional-Methoden für folgende Aufgaben:
  * Überprüfen, ob ein Optional einen Inhalt hat oder nicht
  * Den tatsächlichen Inhalt eines existierenden Optionals abfragen
* Benutzen Sie die Optionals-Methode `.isEmpty()` oder `.isPresent()` um zu überprüfen, ob das Optional existiert oder nicht.
* Von einem existierenden Optional können Sie den tatsächlichen Inhalt mit `.get()` abfragen.
---

## Aufgabe 3 - Streams verstehen und für Sortierungen benutzen
Das Tool soll durch eine Option 4 ergänzt werden: Aufgaben nach Priorität sortiert anzeigen.

In den Klassen `ConsoleView` und `TaskController` befinden sich dazu bereits auskommentierte Code-Snippets, um dem Hauptmenü diese Option hinzuzufügen.

Für die Sortierung von Listen haben wir bereits `.sort()` kombiniert mit Lambda-Ausdrücken kennengelernt. 
Viele Aufgaben im Zusammenhang mit Listen lassen sich jedoch nur mit einer Schleife lösen.

Eine Alternative zum schleifenbasierten Iterieren über Listen sind **Streams**:
Ein Stream ist ein Datenstrom, den wir mit Filtern, Suchen oder Sortierungen manipulieren können.\
Statt mit einer Schleife über eine Liste zu iterieren, können wir sie stattdessen mit `.stream()` in einen Stream umwandeln und diesen verarbeiten.

In diesem Fall soll der Stream sortiert werden. Dies soll in einer Methode `getTaskSortedByPriority()` in der Klasse `TaskService` geschehen.

### Anforderungen
* Vollziehen Sie den vorgegebenen Code aus Aufgabe 2 nach: 
Lesen Sie sich dazu in IntelliJ die Beschreibungen der Methoden `.stream()`, `.filter()` und `.findFirst()` durch.
* Fügen Sie die Option 4 dem Hauptmenü hinzu, indem Sie die entsprechenden Code-Stellen in `TaskController` und `ConsoleView` einkommentieren
* Legen Sie zwei neue Methoden an:
  * Methode `showTasksSortedByPriority()` in der Klasse `TaskController`
    * Diese Methode soll nur die nachfolgende Methode aufrufen und ihr Ergebnis anzeigen
  * Methode `List<Task> getTaskSortedByPriority()` in der Klasse `TaskService`
    * Diese Methode soll alle `Task`s aus dem Attribut `repository` abfragen und diese absteigend nach Priorität sortieren
    * Der Rückgabetyp soll wieder eine Liste sein
    * Benutzen Sie in dieser Methode einen Stream kombiniert mit einem Lambda-Ausdruck, um die `Task`s zu sortieren
    * Orientieren Sie sich für die Syntax am vorgegebenen Code aus Aufgabe 2
* Am Ende der Umsetzung steht dem Benutzer eine Option 4 im Hauptmenü zur Verfügung, die ihm die Aufgaben absteigend nach Priorität sortiert anzeigt. 

### Tipps
* Um einen Stream zu sortieren, steht Ihnen die Methode `.sorted()` zur Verfügung
* Wenn nach einem Enum sortiert wird, wird standardmäßig in der Reihenfolge sortiert, in der die Enums deklariert wurden.
Erinneren Sie sich ans letzte Projekt, wie Sie die Reihenfolge beim Sortieren umdrehen konnten!
* Verwenden Sie die Methode `.compareTo()` in Ihrem Lambda-Ausdruck
* Einen fertig bearbeiteten Stream können Sie mit `.toList()` in einer Liste speichern
* Orientieren Sie sich bei der neuen Controller-Methode an der sehr ähnlichen Methode `showAllTasks()`
---

## Augabe 4 - Suche nach ID
In Aufgabe 2 haben Sie bereits die Statusänderung mit Optionals umgesetzt, bei der nach einer bestimmten Aufgabe anhand der ID gesucht wird.
Nun soll auch der Benutzer die Möglichkeit haben, nach einer Aufgabe per ID zu suchen.

### Anforderungen
* Fügen Sie die Option 5 dem Hauptmenü hinzu, indem Sie die entsprechenden Code-Stellen in `TaskController` und `ConsoleView` einkommentieren
* Ergänzen Sie erneut eine Methode im Controller und eine im Service
* Die Service-Methode `findTaskByID()`:
  * Sucht im Repository nach dem Task mit der passenden ID (hierzu steht Ihnen bereits eine Methode zur Verfügung!)
  * Hat den Rückgabetyp `Optional<Task>`
* Die Controller-Methode `findTaskById()`:
  * Liest eine vom User angegebene ID ein (hierzu steht Ihnen bereits eine Methode zur Verfügung!)
  * Ruft die Service-Methode `findTaskByID()` auf und verarbeitet das Ergebnis
  * Zeigt den Task an, wenn es die ID gibt
  * Zeigt den Text `"Keine Aufgabe mit dieser ID gefunden"` an, wenn es die ID nicht gibt
* Am Ende der Umsetzung steht dem Benutzer eine Option 5 zum Anzeigen einer Aufgabe anhand der ID zur Verfügung

### Tipps
* Für Konsolen-Ausgabe/Eingabe stehen Ihnen die Wrapper-Methoden `view.showMessage()` und `view.readTaskId()` zur Verfügung
* Benutzen Sie erneut die Methoden `.isEmpty()` oder `.isPresent()` um zu überprüfen, ob ein Optional einen Inhalt hat
---

## Aufgabe 5 - Nach Status filtern mit Streams
In Aufgabe 3 haben Sie Streams zum Sortieren benutzt, was nicht unbedingt der klassische Anwendungsfall für Streams ist.
Meistens werden Sie für Filter- und Suchaufgaben verwendet.

Nun sollen mithilfe von Streams die Aufgaben nach einem gegebenen Status gefiltert werden.

### Anforderungen
* Fügen Sie die Option 6 dem Hauptmenü hinzu, indem Sie die entsprechenden Code-Stellen in `TaskController` und `ConsoleView` einkommentieren
* Ergänzen Sie erneut eine Methode im Controller und eine im Service
* Die Service-Methode `getTasksByStatus()`:
  * Diese Methode soll für einen gegebenen `TaskStatus` eine Liste der `Task`s mit diesem Status ausgeben
  * Die Methode soll statt einer Schleife einen Stream zum Filtern verwenden
  * Orientieren Sie sich bei der Umsetzung an den bereits geschriebenen Methoden `service.getTasksSortedByPriority()` und `repository.findById()`
* Die Controller-Methode:
  * Identifizieren Sie selbst den Namen der Methode, die Sie anlegen müssen
  * Die Methode liest einen vom User gegebenen `TaskStatus` ein (hierzu steht Ihnen bereits eine Methode zur Verfügung!)
  * Anschließend ruft Sie die Service-Methode auf und zeigt das Ergebnis an
* Am Ende der Umsetzung steht dem Benutzer eine Option 6 im Hauptmenü zur Verfügung, mit der er Aufgaben nach Status filtern kann

### Tipps
* Der Name der zu implementierenden Controller-Methode steht in dem Code, den Sie einkommentiert haben
* Zum Einlesen eines `TaskStatus` von der Konsole steht Ihnen die Methode `view.readTaskStatus()` zur Verfügung
* Um einen Stream zu erzeugen, benötigen Sie zunächst eine Liste als Ausgangsbasis. Eine Liste aller `Task`s können Sie mit `repository.findAll()` erhalten.
* Benutzen Sie die Methode `.filter()` gepaart mit einem Lambda-Ausdruck, um Ihren Stream nach dem gewünschten Status zu filtern.
Wie wird in `repository.findById()` nach der ID gefiltert?
* Den gefilterten Stream können Sie mit `.toList()` wieder in eine Liste umwandeln
---

## Aufgabe 6 - Statistiken anzeigen mit Streams
Zuletzt sollen Streams genutzt werden, um dem Nutzer eine Statistik seiner Aufgaben anzuzeigen.
Die Statistik soll anzeigen, wie viele Aufgaben jeweils die Zustände `OPEN`, `IN_PROGRESS` und `DONE` haben.

Die Statistik-Anzeige soll ungefähr so aussehen:
```text
=== Aufgabenstatistik ===
Aufgaben insgesamt: 10
Offen:              6
In Bearbeitung:     3
Erledigt:           1
```
### Anforderungen
* Fügen Sie die Option 7 dem Hauptmenü hinzu, indem Sie die entsprechenden Code-Stellen in `TaskController` und `ConsoleView` einkommentieren
* Für die Statistik-Anzeige gibt es in `ConsoleView` z.Zt. noch keine Methode. Ergänzen Sie dort die Methode `showStatistics()`, die eine Anzeige entsprechend der Vorgabe erzeugt.
Überlegen Sie sich hierzu, welche Parameter man der Methode mitgeben muss, damit sie die Werte anzeigen kann.
* Legen Sie im Service die Klasse `countTasksByStatus()` an:
  * Die Methode gibt für eine gegebenen Status aus, wieviele Aufgaben diesen Status haben
  * Benutzen Sie in dieser Methode erneut einen Stream gepaart mit einem Lambda-Ausdruck. Es gibt eine Methode, die Ihnen die Anzahl der Elemente eines Streams zurückgibt.
* Legen Sie im Controller die passende Methode an, die die neue Service-Methode mit jedem Status (also dreimal) aufruft und die Ergebnisse an die View weiterleitet
* Am Ende der Umsetzung steht dem Benutzer eine Option 7 im Hauptmenü zur Verfügung, die ihm eine Statistik der Aufgaben anzeigt
---

## Aufgabe 7 - Kommandos verwalten mit dem Command-Pattern
Die letzte Funktion soll es ermöglichen, das Anlegen einer Aufgabe oder das Ändern Ihres Staus rückgängig zu machen.
Das stellt uns vor das Problem, dass Aktionen irgendwie vorübergehend gespeichert werden müssen - sonst weiß das Programm nicht, was es rückgängig machen soll.

Ein Weg, um durchgeführte Aktionen zu speichern, ist das Design-Pattern **Command**. 
Mit diesem Pattern kann man die Handlung eines Programms (also z.B. einen Methodenaufruf oder ein sonstiges **Kommando**) selbst als Objekt speichern.\
Dieses Kommando-Objekt kann dann z.B. in einem Verlauf angezeigt werden oder dazu verwendet werden, die zugehörige Handlung rückgängig zu machen.

Ein Kommando, das verwaltet werden soll, können wir als Klasse definieren. In unserem Fall sind das folgende Kommandos:
* Neue Aufgabe hinzufügen (Klasse: `AddTaskCommand`)
* Status einer Aufgabe ändern (Klasse: `ChangeTaskStatusCommand`)

Da beide Kommandos ähnliche Eigenschaften haben, implementieren sie beide das Interface `Command`, das folgendermaßen aussieht:
```java
public interface Command {
    void execute();
    void undo();
}
```
* Die Methode `execute()` führt das beschriebene Kommando aus
* Die Methode `undo()` macht das Kommando rückgängig

Die Kommandos werden vom `CommandInvoker` verwaltet, der sie in einer Schlange (engl. Queue) abspeichert, damit die Reihenfolge fest bleibt:
```java

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
```
Die Klasse `AddTaskCommand` dient nun als Wrapper für die Methode `service.createTask()`:
```java
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
```

### Anforderungen
* Fügen Sie die Option 8 dem Hauptmenü hinzu, indem Sie die entsprechenden Code-Stellen in `TaskController` und `ConsoleView` einkommentieren
* Legen Sie das Package `command` an. Legen Sie darin folgende Klassen an:
  * `Command` entsprechend der Beschreibung
  * `CommandInvoker` entsprechend der Beschreibung
  * `AddTaskCommand` entsprechend der Beschreibung
  * `ChangeTaskStatusCommand` implementiert `Command`, sonst zunächst leer lassen
* Stellen Sie den Invoker dem restlichen Programm zur Verfügung:
  * Geben Sie dem Controller ein weiteres Attribut `invoker`, das auch als Parameter im Konstruktor ergänzt wird
  * Deklarieren und initialisieren Sie eine Instanz der Klasse `Invoker` in der `Main`-Methode und ergänzen Sie die Instanz als Attribut im in der Initialisierung des Controllers
* Passen Sie nun die Methode `createTask()` im Controller an:
  * Anstatt hier direkt den Service aufzurufen, soll nun stattdessen über `invoker.execute()` die Methode `AddTaskCommand.execute()` aufgerufen werden
  * Vollziehen Sie hierzu im vorgegebenen Code nach, wie Sie mithilfe der Klasse `CommandInvoker` ein Kommando `AddTaskCommand` ausführen könnten
* Ergänzen Sie nun die Methode `undoLastAction()` im Controller:
  * Diese Methode soll die Methode `invoker.undo()` aufrufen und ihr Ergebnis verarbeiten
  * Folgende Nachrichten sollen auf die Konsole ausgegeben werden:
    * Bei Erfolg: `"Letzte Aktion rückgängig gemacht"`
    * Bei Misserfolg: `"Keine Aktion zum Rückgängig machen vorhanden"`
* Am Ende dieser Umsetzung soll es dem Benutzer möglich sein, das Anlegen einer Aufgabe rückgängig zu machen

### Tipps
* Die zur Verfügung gestellten Klassen enthalten alles, außer die Package-Deklaration. Fügen Sie diese unbedingt hinzu!
* In der `Main`-Methode müssen Sie den `CommandInvoker` als Import ergänzen. Auch im Controller müssen Sie Imports ergänzen.
* `invoker.execute()` nimmt als Parameter ein Objekt vom Typ `Command` entgegen, in unserem Fall vom Typ `AddTaskCommand`.
Legen Sie also in der `createTask()`-Methode zunächst ein solches Objekt an - überprüfen Sie, welche Parameter der Konstruktor benötigt.
Ihnen stehen all diese Werte in der Methode bereits zur Verfügung!
* Die Methode `undo()` gibt `true` zurück, wenn es eine Aktion gibt, die rückgängig gemacht werden konnte.
---

## Aufgabe 8 - Statusänderungen als Kommando darstellen
Das Kommando "Aufgabe anlegen" wird durch die Klasse `AddTaskCommand` bereits dargestellt.
Nun soll das Kommando "Aufgabenstatus ändern" durch die Klasse `ChangeTaskStatusCommand` dargestellt werden.

Dadurch können beide Aktionen in unserem Tool anschließend rückgängig gemacht werden.

### Anforderungen
* Ergänzen Sie die Implementierung der Klasse `ChangeTaskStatusCommand`
  * Vollziehen Sie dazu den Code in `AddTaskCommand` gründlich nach und orientieren Sie sich bei der Umsetzung eng an diesem Code
  * In der `execute()`-Methode muss hier geprüft werden, ob die `Task` mit der gesuchten ID existiert. Wenn sie nicht existiert, brechen Sie die Methode einfach vorzeitig ab.
  * In der `undo()`-Methode muss der Task auf seinen vorherigen Status zurückgesetzt werden. Hierzu muss das Kommando sich diesen vorherigen Status an irgendeiner Stelle merken. Überlegen Sie sich, wie sie das umsetzen könnten.
* Passen Sie nun den Controller so an, dass er bei der Statusänderung nicht mehr direkt den Service aufruft, sondern auch hier den Weg über den Invoker geht
  * Die Umsetzung ist auch hier der aus der vorherigen Aufgabe sehr ähnlich
* Am Ende dieser Umsetzung ist es dem Benutzer möglich, Statusänderungen rückgängig zu machen

### Tipps
* Überlegen Sie sich zunächst, welche Attribute das Kommando braucht, um eine Statusänderung durchzuführen.
Die Attribute entsprechen logisch den Parametern der Methode `service.changeTaskStatus()`.
* Um sich den vorherigen Status zu merken, eignet sich ein weiteres Attribut `TaskStatus previousStatus`. 
Dieses befüllen Sie in der `execute()`-Methode mit dem aktuellen Status, bevor Sie die Statusänderung durchführen.
* In der `undo()`-Methode müssen Sie erneut `service.changeTaskStatus()` aufrufen, nur diesmal mit dem Argument `previousStatus`, um den vorherigen Zustand wiederherzustellen.

### Erweiterungsvorschläge
Momentan wird das Ergebnis der Statusänderung ignoriert, d.h. auch bei Misserfolg bekommt der User die Rückmeldung, die Änderung sei ausgeführt worden.
Das liegt daran, dass die Methode `execute()` keinen Rückgabewert hat, den der Controller verarbeiten könnte.
Passen Sie Ihre Implementierung des Command-Patterns so an, dass `execute()` einen `boolean` als Rückgabetyp hat.