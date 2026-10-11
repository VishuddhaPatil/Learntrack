 — Design Notes

## 1. Project Overview

LearnTrack is a console-based Student and Course Management System built using Core Java. The application follows an object-oriented design and separates data models, business logic, utility functions, and user interaction.

## 2. Architecture

The project uses four main layers or components:

- **Entity:** Represents the core data models.
- **Service:** Implements business logic and manages records.
- **Exception:** Defines custom exceptions for errors.
- **Utility:** Generates unique IDs during the application's runtime.

`Main.java` provides the console interface and connects the services.

## 3. Class Responsibilities

| Class | Responsibility |
|---|---|
| `Person` | Stores common personal details such as ID, name, and email. |
| `Student` | Extends Person and adds batch and active status. |
| `Course` | Stores course information, duration, and active status. |
| `Enrollment` | Represents a student's enrollment in a course. |
| `StudentService` | Adds, lists, searches, updates, and deactivates students. |
| `CourseService` | Adds, lists, searches, updates, activates, and deactivates courses. |
| `EnrollmentService` | Creates and manages enrollments and their statuses. |
| `IdGenerator` | Generates IDs for students, courses, and enrollments. |
| `EntityNotFoundException` | Reports when a requested entity cannot be found. |
| `InvalidInputException` | Reports invalid input provided to service methods. |
| `Main` | Displays menus and accepts administrator input. |

## 4. Object-Oriented Programming Concepts

### Encapsulation

Entity classes use private fields and public getters and setters to control access to data.

### Inheritance

`Student` extends `Person`, allowing it to reuse common personal information and methods.

### Polymorphism

`Student` overrides `getDisplayName()` from `Person` to provide student-specific display behavior.

### Abstraction and Separation of Responsibilities

The service classes expose methods for business operations, allowing `Main` to call them without managing the internal lists directly.

## 5. Data Structures

The application uses `ArrayList` through the `List` interface to store students, courses, and enrollments.

These collections are held in memory. The current version does not persist data after the application exits.

## 6. Enrollment Rules

- A student must exist before being enrolled.
- A course must exist before being enrolled.
- Inactive students and inactive courses cannot be used for new enrollments.
- A student cannot have duplicate active enrollments for the same course.
- An active enrollment can be marked as completed or cancelled.

## 7. Exception Handling

Custom exceptions communicate invalid input and missing entities. The console application catches exceptions and displays an error message rather than terminating the application for ordinary operation errors.

## 8. Limitations

- Data is stored only in memory.
- IDs are generated at runtime and reset when the application restarts.
- The application has a console-based interface.
- There is no database or authentication system.

## 9. Future Enhancements

- Integrate a relational database.
- Add persistent ID generation.
- Implement automated unit tests.
- Add administrator authentication.
- Develop a web-based interface.
