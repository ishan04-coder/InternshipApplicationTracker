# Internship Application Tracker

## Project Overview

The Internship Application Tracker is a Java command-line project made to help a student keep track of internship applications.

Instead of keeping company names, deadlines and application status in different places, the user can manage them from one menu.

## Features

- Add an internship application
- View all applications
- Search by company, role, location or status
- Change application status
- Check upcoming deadlines
- View application statistics
- Delete an application
- Save data in a local file
- Input validation
- Exception handling

## Application Status

The available statuses are:

- APPLIED
- SHORTLISTED
- INTERVIEW
- SELECTED
- REJECTED

## Requirements

- Java JDK 8 or newer
- Windows Command Prompt, PowerShell, or a terminal
- No external Java libraries

## Project Structure

```text
InternshipApplicationTracker/
│
├── src/
│   ├── Main.java
│   ├── InternshipApplication.java
│   ├── ApplicationManager.java
│   ├── ApplicationStore.java
│   └── Status.java
│
├── data/
│   └── applications.txt
│
├── docs/
│   ├── PROJECT_REPORT.md
│   └── DEMO_STEPS.md
│
├── run.bat
├── run.sh
├── .gitignore
└── README.md
```

## How to Run on Windows

Open Command Prompt in the project folder and run:

```text
run.bat
```

Or run manually:

```text
javac -d out src\*.java
java -cp out Main
```

## Data Storage

Application records are stored in:

```text
data/applications.txt
```

The program loads the records when it starts and saves changes when an application is added, updated or deleted.

## Java Concepts Used

- Classes and objects
- Encapsulation
- Enum
- ArrayList
- Methods
- Loops
- Conditional statements
- File handling
- Exception handling
- LocalDate
- Sorting with Comparator
- Command-line input

## Evaluation

The project is designed to run directly from the command line. No GUI setup or internet connection is required.
