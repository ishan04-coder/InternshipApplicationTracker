# PROJECT REPORT
# Internship Application Tracker

## 1. Introduction

Applying for internships usually involves several companies, different deadlines and different stages of selection.

This project provides a simple command-line application where a student can store and manage all of these details in one place.

## 2. Problem Statement

Students may forget application deadlines or lose track of whether an application is still under review, shortlisted, in an interview stage, selected or rejected.

The proposed system keeps these details together and provides simple search and tracking features.

## 3. Objectives

1. Store internship application details.
2. Track the current status of each application.
3. Search applications quickly.
4. Check upcoming deadlines.
5. Show basic application statistics.
6. Store records so that they remain available after the program is closed.
7. Demonstrate Java programming and object-oriented concepts.

## 4. Features

### Add Application

The user enters:

- Company
- Role
- Location
- Deadline
- Status
- Notes

The program assigns a unique ID.

### View Applications

All saved applications are displayed in a table and sorted by deadline.

### Search

The user can search using a word related to:

- Company
- Role
- Location
- Status

### Update Status

An existing application can be changed to another status.

### Upcoming Deadlines

The user enters a number of days. The program shows applications whose deadlines fall within that period.

### Statistics

The program counts applications in each status.

### Delete

An application can be removed using its ID.

## 5. Program Design

```text
                 Main
                   |
                   v
          ApplicationManager
             /           \
            v             v
 InternshipApplication   ApplicationStore
                            |
                            v
                    applications.txt
```

## 6. Classes

### Main

Handles the menu and user input.

### InternshipApplication

Represents one internship application and stores its details.

### ApplicationManager

Contains the main operations such as adding, searching, updating, deleting and checking deadlines.

### ApplicationStore

Loads and saves application records in a text file.

### Status

An enum containing the possible application statuses.

## 7. Object-Oriented Concepts

### Encapsulation

The fields in `InternshipApplication` are private and accessed using methods.

### Classes and Objects

Each internship application is represented as an object.

### Enum

`Status` is used because the application status comes from a fixed set of values.

### Separation of Responsibilities

The menu, application logic and file storage are kept in different classes.

## 8. File Handling

The project uses a local text file:

```text
data/applications.txt
```

Each application is stored as one record.

This means the program does not lose its data when it is closed.

## 9. Exception Handling

The program handles:

- Invalid numbers
- Invalid dates
- File reading problems
- File writing problems
- Invalid saved records

## 10. Testing

| Test | Expected Result |
|---|---|
| Add valid application | Application is saved |
| Empty company | User is asked again |
| Invalid date | User is asked for correct format |
| Search existing company | Matching record appears |
| Search unknown word | No applications found |
| Update existing ID | Status changes |
| Invalid ID | Error message |
| Delete existing ID | Application is removed |
| View statistics | Status counts appear |
| Restart program | Saved records are loaded |

## 11. Limitations

- It is designed for one local user.
- Data is stored in a text file instead of a database.
- It does not send real email notifications.
- It does not have a graphical interface.

## 12. Future Scope

The project can later be extended with:

- MySQL database
- Login system
- Resume tracking
- Interview notes
- CSV export
- Email reminders
- GUI
- Web version
- Calendar integration

## 13. Conclusion

The Internship Application Tracker is a small practical Java application for organizing internship applications. It demonstrates object-oriented programming, collections, file handling, date handling, searching, sorting and exception handling through a command-line interface.
