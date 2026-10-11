
package com.airtribe.learntrack;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;

public class ManagementServiceTest {

    public static void main(String[] args) {

        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();

        EnrollmentService enrollmentService =
            new EnrollmentService(studentService, courseService);

        try {
            Student student = studentService.addStudent(
                "Vishuddha", "Patil",
                "vishuddha@example.com", "Java Batch 1"
            );

            Course course = courseService.addCourse(
                "Core Java",
                "Java basics and OOP",
                8
            );

            Enrollment enrollment =
                enrollmentService.enrollStudent(
                    student.getId(), course.getId()
                );

            System.out.println("Student created: "
                + student.getDisplayName());

            System.out.println("Course created: "
                + course.getCourseName());

            System.out.println("\nEnrollment successful!");
            enrollment.displayEnrollment();

            enrollmentService.completeEnrollment(
                enrollment.getId()
            );

            System.out.println("\nAfter completion:");
            System.out.println(enrollment.getStatus());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
