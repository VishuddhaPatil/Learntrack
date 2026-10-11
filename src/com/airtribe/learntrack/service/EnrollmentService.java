
package com.airtribe.learntrack.service;

import java.util.ArrayList;
import java.util.List;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.util.IdGenerator;

public class EnrollmentService {

    private final List<Enrollment> enrollments = new ArrayList<>();

    private final StudentService studentService;
    private final CourseService courseService;

    public EnrollmentService(
            StudentService studentService,
            CourseService courseService) {

        this.studentService = studentService;
        this.courseService = courseService;
    }

    public Enrollment enrollStudent(int studentId, int courseId)
            throws EntityNotFoundException, InvalidInputException {

        Student student = studentService.findStudentById(studentId);
        Course course = courseService.findCourseById(courseId);

        if (!student.isActive()) {
            throw new InvalidInputException(
                "Cannot enroll an inactive student."
            );
        }

        if (!course.isActive()) {
            throw new InvalidInputException(
                "Cannot enroll in an inactive course."
            );
        }

        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudentId() == studentId
                    && enrollment.getCourseId() == courseId
                    && "ACTIVE".equals(enrollment.getStatus())) {

                throw new InvalidInputException(
                    "Student is already enrolled in this course."
                );
            }
        }

        Enrollment enrollment = new Enrollment(
            IdGenerator.getNextEnrollmentId(),
            studentId,
            courseId
        );

        enrollments.add(enrollment);
        return enrollment;
    }

    public List<Enrollment> listEnrollments() {
        return new ArrayList<>(enrollments);
    }

    public List<Enrollment> getEnrollmentsForStudent(int studentId)
            throws EntityNotFoundException {

        studentService.findStudentById(studentId);

        List<Enrollment> result = new ArrayList<>();

        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudentId() == studentId) {
                result.add(enrollment);
            }
        }

        return result;
    }

    public void completeEnrollment(int enrollmentId)
            throws EntityNotFoundException, InvalidInputException {

        Enrollment enrollment = findEnrollmentById(enrollmentId);

        if (!"ACTIVE".equals(enrollment.getStatus())) {
            throw new InvalidInputException(
                "Only active enrollments can be completed."
            );
        }

        enrollment.setStatus("COMPLETED");
    }

    public void cancelEnrollment(int enrollmentId)
            throws EntityNotFoundException, InvalidInputException {

        Enrollment enrollment = findEnrollmentById(enrollmentId);

        if (!"ACTIVE".equals(enrollment.getStatus())) {
            throw new InvalidInputException(
                "Only active enrollments can be cancelled."
            );
        }

        enrollment.setStatus("CANCELLED");
    }

    private Enrollment findEnrollmentById(int id)
            throws EntityNotFoundException {

        for (Enrollment enrollment : enrollments) {
            if (enrollment.getId() == id) {
                return enrollment;
            }
        }

        throw new EntityNotFoundException(
            "Enrollment with ID " + id + " was not found."
        );
    }
}
