# CYBER DETECTIVE — The Missing Prototype

A GUI-only mystery investigation game built with standard Java Swing and AWT. Play as a detective, explore four locations, collect five clues, and identify who stole a valuable technology prototype.

## Features

- Dark, card-based investigation dashboard with evidence progress.
- Four investigation locations with five unique clues (the Office yields two clues in its single visit).
- One-time discovery for each location and an Evidence Room that reveals only found clues.
- Four suspects, a gated accusation flow, success and failure outcomes, evidence review, and case restart.
- No external libraries, database, network access, or command-line gameplay.

## Technologies used

- Java 17 or later
- Java Swing and AWT
- Core Java collections and object-oriented programming

## Project structure

```text
CYBER DETECTIVE/
├── README.md
└── src/
    ├── Main.java
    ├── GameFrame.java
    ├── Case.java
    ├── Clue.java
    ├── Suspect.java
    ├── EvidenceManager.java
    ├── InvestigationPanel.java
    ├── EvidenceFrame.java
    ├── SuspectSelectionFrame.java
    └── ResultFrame.java
```

## How to run

From the project root in a terminal with JDK 17+ installed:

```powershell
javac -d out src\*.java
java -cp out Main
```

In VS Code, open this folder, ensure a JDK and the Java extensions are available, then run `src/Main.java` with **Run Java**. No Maven or Gradle setup is required.

## How the game works

Investigate Office, Computer Room, Parking Area, and Apartment. Each location can be visited once. Office contains two related clues, bringing the total to five. The Evidence Room displays discovered items only. Solve Case stays gated until all five clues are found. Select one of the four suspects and submit an accusation. Restart Case clears evidence, re-enables locations, and resets the case state.

## Java concepts demonstrated

- Classes, constructors, encapsulation, and immutable model objects
- Collections and separation of model, game state, and UI responsibilities
- Swing frames, dialogs, layouts, event listeners, radio buttons, and button groups
- Swing event dispatch thread and basic input validation
