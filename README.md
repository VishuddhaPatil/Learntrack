\# LearnTrack – Student and Course Management System



\## Overview

LearnTrack is a console-based Java application for managing students, courses, and enrollments. It demonstrates core Java programming concepts and object-oriented design.



\## Features

\- Add, view, search, update, and deactivate students.

\- Create, view, search, update, and manage courses.

\- Enroll students in courses.

\- View enrollments by student.

\- Mark enrollments as completed or cancelled.

\- Validate user input and handle exceptions.

\- Prevent duplicate active enrollments.



\## Technologies Used

\- Java

\- Object-Oriented Programming (OOP)

\- ArrayList and Java Collections

\- Exception Handling

\- LocalDate

\- Scanner for console input



\## Project Structure

```text

LearnTrack/

├── src/

│   └── com/airtribe/learntrack/

│       ├── entity/

│       ├── exception/

│       ├── service/

│       ├── util/

│       └── Main.java

├── docs/

├── .gitignore

└── README.md

```



\## Requirements

\- Java Development Kit (JDK) installed.

\- Command Prompt or another terminal.



\## How to Compile and Run

Open a terminal in the project root and run:



```bash

javac -d out -sourcepath src src/com/airtribe/learntrack/Main.java

java -cp out com.airtribe.learntrack.Main

```



On Windows, the same commands work with backslashes in the source path.



\## OOP Concepts Demonstrated

\- \*\*Encapsulation:\*\* Entity classes manage their fields through methods.

\- \*\*Inheritance:\*\* Student extends Person.

\- \*\*Polymorphism:\*\* Shared parent-class behavior supports specialized entities.

\- \*\*Abstraction through services:\*\* Service classes manage business logic separately from the console interface.



\## Data Storage

The application stores data in memory using collections. Data is reset when the application exits.



\## Future Improvements

\- Persistent database storage.

\- Automated unit tests.

\- A graphical user interface or web interface.



\## Input Validation and Error Handling



The application validates console input and handles invalid entries using exception handling. It also prevents duplicate active enrollments and checks entity availability before performing operations.



\## Testing



The project includes Java test files for student and management service functionality.



\## Author

Vishuddha Patil

