
package com.airtribe.learntrack.service;

import java.util.ArrayList;
import java.util.List;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.util.IdGenerator;

public class StudentService {

    private final List<Student> students = new ArrayList<>();

    public Student addStudent(
            String firstName,
            String lastName,
            String email,
            String batch) throws InvalidInputException {

        if (firstName == null || firstName.trim().isEmpty()) {
            throw new InvalidInputException(
                "First name cannot be empty."
            );
        }

        if (lastName == null || lastName.trim().isEmpty()) {
            throw new InvalidInputException(
                "Last name cannot be empty."
            );
        }

        if (email == null || !email.contains("@")) {
            throw new InvalidInputException(
                "Please enter a valid email address."
            );
        }

        if (batch == null || batch.trim().isEmpty()) {
            throw new InvalidInputException(
                "Batch cannot be empty."
            );
        }

        Student student = new Student(
            IdGenerator.getNextStudentId(),
            firstName.trim(),
            lastName.trim(),
            email.trim(),
            batch.trim()
        );

        students.add(student);
        return student;
    }

    public List<Student> listStudents() {
        return new ArrayList<>(students);
    }

    public Student findStudentById(int id)
            throws EntityNotFoundException {

        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }

        throw new EntityNotFoundException(
            "Student with ID " + id + " was not found."
        );
    }

    public void deactivateStudent(int id)
            throws EntityNotFoundException {

        Student student = findStudentById(id);
        student.setActive(false);
    }

    public void updateStudent(
            int id,
            String firstName,
            String lastName,
            String email,
            String batch)
            throws EntityNotFoundException, InvalidInputException {

        Student student = findStudentById(id);

        if (firstName == null || firstName.trim().isEmpty()
                || lastName == null || lastName.trim().isEmpty()
                || batch == null || batch.trim().isEmpty()) {
            throw new InvalidInputException(
                "Name and batch cannot be empty."
            );
        }

        if (email == null || !email.contains("@")) {
            throw new InvalidInputException(
                "Please enter a valid email address."
            );
        }

        student.setFirstName(firstName.trim());
        student.setLastName(lastName.trim());
        student.setEmail(email.trim());
        student.setBatch(batch.trim());
    }
}
