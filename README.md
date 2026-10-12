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

## Aufgabe 1
Im Hauptmenü wird bereits die Option "Aufgabenstatus ändern" angezeigt. 
Die Option kann aufgerufen werden und führt den Benutzer durch die Änderung, aber die Änderung wird am Ende nicht durchgeführt.

Ihre Aufgabe ist es, dass die gewünschte Änderung auch tatsächlich durchgeführt wird.

### Anforderungen
* Machen Sie sich zunächst mit dem vorgegebenen Code vertraut. Folgende Fragen sollten Sie beantworten können:
  * Wie wird die Benutzereingabe auf der Konsole verarbeitet?
  * Wo müssten Sie ansetzen, um eine weitere Aufgabenpriorität (z.B. Extrahoch) anzulegen?
  * Werden irgendwo die angelegten Aufgaben dauerhaft gespeichert? Wenn ja, wo?
  * In welcher Datei müssen sie ansetzen, um diese Aufgabe umzusetzen?
* 