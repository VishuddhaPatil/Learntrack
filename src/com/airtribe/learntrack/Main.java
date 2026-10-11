
package com.airtribe.learntrack;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();
        EnrollmentService enrollmentService =
                new EnrollmentService(studentService, courseService);

        boolean running = true;

        System.out.println("==================================");
        System.out.println("       LEARNTRACK SYSTEM");
        System.out.println("==================================");

        while (running) {
            System.out.println("\nMAIN MENU");
            System.out.println("1. Student Management");
            System.out.println("2. Course Management");
            System.out.println("3. Enrollment Management");
            System.out.println("0. Exit");

            int choice = readInt(scanner, "Choose an option: ");

            switch (choice) {
                case 1:
                    studentMenu(scanner, studentService);
                    break;
                case 2:
                    courseMenu(scanner, courseService);
                    break;
                case 3:
                    enrollmentMenu(scanner, studentService,
                            courseService, enrollmentService);
                    break;
                case 0:
                    running = false;
                    System.out.println("Thank you for using LearnTrack!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    // ---------------- STUDENT MENU ----------------

    private static void studentMenu(
            Scanner scanner, StudentService service) {

        boolean back = false;

        while (!back) {
            System.out.println("\nSTUDENT MANAGEMENT");
            System.out.println("1. Add Student");
            System.out.println("2. List Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Update Student");
            System.out.println("5. Deactivate Student");
            System.out.println("0. Back to Main Menu");

            int choice = readInt(scanner, "Choose an option: ");

            try {
                switch (choice) {
                    case 1: {
                        String firstName = readText(scanner, "First name: ");
                        String lastName = readText(scanner, "Last name: ");
                        String email = readText(scanner, "Email: ");
                        String batch = readText(scanner, "Batch: ");

                        Student student = service.addStudent(
                                firstName, lastName, email, batch);

                        System.out.println("Student added successfully!");
                        printStudent(student);
                        break;
                    }

                    case 2: {
                        List<Student> students = service.listStudents();

                        if (students.isEmpty()) {
                            System.out.println("No students found.");
                        } else {
                            for (Student student : students) {
                                printStudent(student);
                            }
                        }
                        break;
                    }

                    case 3: {
                        int id = readInt(scanner, "Enter student ID: ");
                        Student student = service.findStudentById(id);
                        printStudent(student);
                        break;
                    }

                    case 4: {
                        int id = readInt(scanner, "Student ID to update: ");
                        String firstName = readText(scanner, "New first name: ");
                        String lastName = readText(scanner, "New last name: ");
                        String email = readText(scanner, "New email: ");
                        String batch = readText(scanner, "New batch: ");

                        service.updateStudent(
                                id, firstName, lastName, email, batch);

                        System.out.println("Student updated successfully.");
                        break;
                    }

                    case 5: {
                        int id = readInt(scanner, "Student ID to deactivate: ");
                        service.deactivateStudent(id);
                        System.out.println("Student deactivated.");
                        break;
                    }

                    case 0:
                        back = true;
                        break;

                    default:
                        System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // ---------------- COURSE MENU ----------------

    private static void courseMenu(
            Scanner scanner, CourseService service) {

        boolean back = false;

        while (!back) {
            System.out.println("\nCOURSE MANAGEMENT");
            System.out.println("1. Add Course");
            System.out.println("2. List Courses");
            System.out.println("3. Search Course by ID");
            System.out.println("4. Update Course");
            System.out.println("5. Deactivate Course");
            System.out.println("6. Activate Course");
            System.out.println("0. Back to Main Menu");

            int choice = readInt(scanner, "Choose an option: ");

            try {
                switch (choice) {
                    case 1: {
                        String name = readText(scanner, "Course name: ");
                        String description =
                                readText(scanner, "Description: ");
                        int duration =
                                readInt(scanner, "Duration in weeks: ");

                        Course course = service.addCourse(
                                name, description, duration);

                        System.out.println("Course added successfully!");
                        printCourse(course);
                        break;
                    }

                    case 2: {
                        List<Course> courses = service.listCourses();

                        if (courses.isEmpty()) {
                            System.out.println("No courses found.");
                        } else {
                            for (Course course : courses) {
                                printCourse(course);
                            }
                        }
                        break;
                    }

                    case 3: {
                        int id = readInt(scanner, "Enter course ID: ");
                        Course course = service.findCourseById(id);
                        printCourse(course);
                        break;
                    }

                    case 4: {
                        int id = readInt(scanner, "Course ID to update: ");
                        String name = readText(scanner, "New course name: ");
                        String description =
                                readText(scanner, "New description: ");
                        int duration =
                                readInt(scanner, "New duration in weeks: ");

                        service.updateCourse(id, name, description, duration);
                        System.out.println("Course updated successfully.");
                        break;
                    }

                    case 5: {
                        int id = readInt(scanner, "Course ID to deactivate: ");
                        service.deactivateCourse(id);
                        System.out.println("Course deactivated.");
                        break;
                    }

                    case 6: {
                        int id = readInt(scanner, "Course ID to activate: ");
                        service.activateCourse(id);
                        System.out.println("Course activated.");
                        break;
                    }

                    case 0:
                        back = true;
                        break;

                    default:
                        System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // ---------------- ENROLLMENT MENU ----------------

    private static void enrollmentMenu(
            Scanner scanner,
            StudentService studentService,
            CourseService courseService,
            EnrollmentService service) {

        boolean back = false;

        while (!back) {
            System.out.println("\nENROLLMENT MANAGEMENT");
            System.out.println("1. Enroll Student in Course");
            System.out.println("2. List All Enrollments");
            System.out.println("3. List Enrollments by Student ID");
            System.out.println("4. Complete Enrollment");
            System.out.println("5. Cancel Enrollment");
            System.out.println("0. Back to Main Menu");

            int choice = readInt(scanner, "Choose an option: ");

            try {
                switch (choice) {
                    case 1: {
                        // Show IDs so the admin knows what to enter.
                        System.out.println("\nAvailable Students:");
                        List<Student> students = studentService.listStudents();

                        if (students.isEmpty()) {
                            System.out.println("No students available.");
                            break;
                        }

                        for (Student student : students) {
                            printStudent(student);
                        }

                        System.out.println("\nAvailable Courses:");
                        List<Course> courses = courseService.listCourses();

                        if (courses.isEmpty()) {
                            System.out.println("No courses available.");
                            break;
                        }

                        for (Course course : courses) {
                            printCourse(course);
                        }

                        int studentId =
                                readInt(scanner, "Enter student ID: ");
                        int courseId =
                                readInt(scanner, "Enter course ID: ");

                        Enrollment enrollment =
                                service.enrollStudent(studentId, courseId);

                        System.out.println("Enrollment successful!");
                        printEnrollment(enrollment);
                        break;
                    }

                    case 2: {
                        List<Enrollment> enrollments =
                                service.listEnrollments();

                        if (enrollments.isEmpty()) {
                            System.out.println("No enrollments found.");
                        } else {
                            for (Enrollment enrollment : enrollments) {
                                printEnrollment(enrollment);
                            }
                        }
                        break;
                    }

                    case 3: {
                        int studentId =
                                readInt(scanner, "Enter student ID: ");

                        List<Enrollment> enrollments =
                                service.getEnrollmentsForStudent(studentId);

                        if (enrollments.isEmpty()) {
                            System.out.println(
                                    "No enrollments found for this student.");
                        } else {
                            for (Enrollment enrollment : enrollments) {
                                printEnrollment(enrollment);
                            }
                        }
                        break;
                    }

                    case 4: {
                        int id = readInt(
                                scanner, "Enrollment ID to complete: ");

                        service.completeEnrollment(id);
                        System.out.println("Enrollment marked COMPLETED.");
                        break;
                    }

                    case 5: {
                        int id = readInt(
                                scanner, "Enrollment ID to cancel: ");

                        service.cancelEnrollment(id);
                        System.out.println("Enrollment marked CANCELLED.");
                        break;
                    }

                    case 0:
                        back = true;
                        break;

                    default:
                        System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // ---------------- DISPLAY HELPERS ----------------

    private static void printStudent(Student student) {
        System.out.println("\nStudent ID: " + student.getId());
        System.out.println("Name: " + student.getDisplayName());
        System.out.println("Email: " + student.getEmail());
        System.out.println("Batch: " + student.getBatch());
        System.out.println("Active: " + student.isActive());
        System.out.println("------------------------------");
    }

    private static void printCourse(Course course) {
        System.out.println("\nCourse ID: " + course.getId());
        System.out.println("Name: " + course.getCourseName());
        System.out.println("Description: " + course.getDescription());
        System.out.println("Duration: " + course.getDurationInWeeks()
                + " weeks");
        System.out.println("Active: " + course.isActive());
        System.out.println("------------------------------");
    }

    private static void printEnrollment(Enrollment enrollment) {
        System.out.println("\nEnrollment ID: " + enrollment.getId());
        System.out.println("Student ID: " + enrollment.getStudentId());
        System.out.println("Course ID: " + enrollment.getCourseId());
        System.out.println("Enrollment Date: "
                + enrollment.getEnrollmentDate());
        System.out.println("Status: " + enrollment.getStatus());
        System.out.println("------------------------------");
    }

    // ---------------- INPUT HELPERS ----------------

    private static String readText(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(
                        "Please enter a valid whole number, such as 1 or 0.");
            }
        }
    }
}
