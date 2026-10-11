
package com.airtribe.learntrack;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;

public class StudentServiceTest {

    public static void main(String[] args) {

        StudentService service = new StudentService();

        try {
            // 1. Add students
            Student first = service.addStudent(
                "Vishuddha", "Patil",
                "vishuddha@example.com", "Java Batch 1"
            );

            Student second = service.addStudent(
                "Aarav", "Sharma",
                "aarav@example.com", "Java Batch 1"
            );

            System.out.println("Students added successfully!");
            System.out.println("First student ID: " + first.getId());
            System.out.println("Second student ID: " + second.getId());

            // 2. List students
            System.out.println("\n--- All Students ---");

            for (Student student : service.listStudents()) {
                System.out.println(
                    student.getId() + " | "
                    + student.getDisplayName() + " | "
                    + student.getEmail() + " | "
                    + student.getBatch() + " | Active: "
                    + student.isActive()
                );
            }

            // 3. Search by ID
            System.out.println("\n--- Search Student ---");

            Student found = service.findStudentById(first.getId());
            System.out.println("Found: " + found.getDisplayName());

            // 4. Update student
            service.updateStudent(
                first.getId(),
                "Vishuddha", "Patil",
                "vishuddha.patil@example.com", "Java Batch 2"
            );

            System.out.println("\n--- After Update ---");
            System.out.println("Email: " + found.getEmail());
            System.out.println("Batch: " + found.getBatch());

            // 5. Deactivate student
            service.deactivateStudent(second.getId());

            System.out.println("\n--- After Deactivation ---");
            System.out.println(
                second.getDisplayName()
                + " | Active: " + second.isActive()
            );

            // 6. Test custom exception
            System.out.println("\n--- Test Missing Student ---");

            try {
                service.findStudentById(999);
            } catch (EntityNotFoundException e) {
                System.out.println("Handled correctly: " + e.getMessage());
            }

        } catch (InvalidInputException | EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
