 — Setup Instructions

## 1. Requirements

Install a Java Development Kit (JDK) on your computer.

Verify the installation in Command Prompt:

```bat
java -version
javac -version
```

Both commands should display version information.

## 2. Project Location

The project root directory is:

```text
C:\Users\msvis\Documents\LearnTrack
```

The main application source file is:

```text
src\com\airtribe\learntrack\Main.java
```

## 3. Compile the Application

Open Command Prompt and execute:

```bat
cd C:\Users\msvis\Documents\LearnTrack
javac -d out -sourcepath src src\com\airtribe\learntrack\Main.java
```

The `-d out` option places compiled `.class` files in the `out` directory.

The `-sourcepath src` option tells the Java compiler where to find source files required by the application.

## 4. Run the Application

After compilation succeeds, execute:

```bat
java -cp out com.airtribe.learntrack.Main
```

The main menu will appear in the console.

## 5. Using the Application

From the main menu, select:

- `1` for Student Management
- `2` for Course Management
- `3` for Enrollment Management
- `0` to exit

Follow the submenu instructions to perform each operation.

## 6. Troubleshooting

### `javac` is not recognized

Check that the JDK is installed and that its `bin` directory is configured in the system PATH.

### `Could not find or load main class`

Confirm that compilation completed successfully and that you are running the command from the project root with the correct class name.

### Compilation errors

Check the full error message, verify package declarations and imports, and ensure the source files are saved in their correct folders.

### Records disappear after restarting

This version uses in-memory collections. Student, course, and enrollment data are not saved between application runs.

## 7. Recompiling After Changes

Whenever you modify a Java source file, compile the application again before running it:

```bat
javac -d out -sourcepath src src\com\airtribe\learntrack\Main.java
java -cp out com.airtribe.learntrack.Main
```
