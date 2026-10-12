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
* Benutzen Sie die Optionals-Methode `.isEmpty()` um zu überprüfen, ob das Optional existiert oder nicht.
* Von einem existierenden Optional können Sie den tatsächlichen Inhalt mit `.get()` abfragen.

---
## Aufgabe 3 - Streams verstehen und für Sortierungen benutzen

