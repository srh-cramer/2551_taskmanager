# Projekt 2 Ergebnisse
## Optionals
Optionals sind Behälter, die einen Inhalt haben können oder nicht.

Wichtigste Eigenschaften:
* wird meistens bei Methoden verwendet, die möglicherweise keinen passenden Wert finden (z.B. Lookup per ID)
* ist eine Sicherheitsfunktion, um NullPointerExceptions zu vermeiden
* hilft dabei, fehlende Rückgabewerte explizit zu behandeln
* Typensicherer Container (d.h. Datentyp kann sich nach Deklaration nicht ändern)

Wichtige Optional-Methoden:
* `.isPresent()`: Prüft, ob ein Wert vorhanden ist
* `.isEmpty()`: Prüft, ob ein Wert *nicht* vorhanden ist
* `.get()`: Greift auf den Inhalt zu, wenn er vorhanden ist
* `.orElse(defaultValue)`: Greift auf Inhalt zu, wenn vorhanden, nimmt sonst `defaultValue`
* `Optional.of(value)`: Erstellt aus `value` ein `Optional<Value>`

Code-Snippet:
```java
import java.util.Optional;

Optional<Task> task = repository.findById(id);
```
## Streams
Streams erlauben die deklarative Verarbeitung von Datenströmen und sind damit eine Alternative zur Schleife.

Wichtigste Eigenschaften:
* Datenquellen sind meistens Listen oder andere iterierbare Objekte
* ermöglicht eine fließbandartige Verarbeitung der Daten in mehreren Schritten
* werden meistens verwendet zum Filtern, Suchen, Sortieren
* Die ursprünglichen Daten können dabei nicht verändert werden
* wird von Java als Klasse bzw. Methode bereitgestellt
* Möglichkeit, große Datenmengen zu verarbeiten, ohne sie zu speichern

Wichtige Stream-Methoden:
* `.stream()`: Verwandelt iterierbares Objekt (z.B. Liste) in einen Stream
* `.filter()`: Filtert nach Filterkriterien, üblicherweise als Lambda-Ausdruck definiert
* `.sorted()`: Sortiert nach Sortierkriterium (`Comparator`), üblicherweise als Lambda-Ausdruck definiert
* `.map()`: Erstellt pro Element ein neues Element
* `.toList()`: Verwandelt Stream in Liste
* `.findFirst()`: Gibt das erste Element des Streams aus als `Optional`

Code-Snippet:
```java
List<Task> myTasks = new ArrayList<Task>();
List<Task> filteredTasks = myTasks.stream() // Mache Liste zu Stream
        .filter(task -> task.getStatus() == TaskStatus.OPEN) // Filtere Stream nach TaskStatus.OPEN
        .sorted((task1, task2) -> task1.getPriority().compareTo(task2.getPriority())) // Sortiere Stream nach Priorität
        .toList(); // Mache Stream zu Liste
```