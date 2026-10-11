
package com.airtribe.learntrack.service;

import java.util.ArrayList;
import java.util.List;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.util.IdGenerator;

public class CourseService {

    private final List<Course> courses = new ArrayList<>();

    public Course addCourse(
            String courseName,
            String description,
            int durationInWeeks) throws InvalidInputException {

        if (courseName == null || courseName.trim().isEmpty()) {
            throw new InvalidInputException(
                "Course name cannot be empty."
            );
        }

        if (description == null || description.trim().isEmpty()) {
            throw new InvalidInputException(
                "Course description cannot be empty."
            );
        }

        if (durationInWeeks <= 0) {
            throw new InvalidInputException(
                "Duration must be greater than zero."
            );
        }

        Course course = new Course(
            IdGenerator.getNextCourseId(),
            courseName.trim(),
            description.trim(),
            durationInWeeks
        );

        courses.add(course);
        return course;
    }

    public List<Course> listCourses() {
        return new ArrayList<>(courses);
    }

    public Course findCourseById(int id)
            throws EntityNotFoundException {

        for (Course course : courses) {
            if (course.getId() == id) {
                return course;
            }
        }

        throw new EntityNotFoundException(
            "Course with ID " + id + " was not found."
        );
    }

    public void deactivateCourse(int id)
            throws EntityNotFoundException {

        Course course = findCourseById(id);
        course.setActive(false);
    }

    public void activateCourse(int id)
            throws EntityNotFoundException {

        Course course = findCourseById(id);
        course.setActive(true);
    }

    public void updateCourse(
            int id,
            String courseName,
            String description,
            int durationInWeeks)
            throws EntityNotFoundException, InvalidInputException {

        Course course = findCourseById(id);

        if (courseName == null || courseName.trim().isEmpty()
                || description == null || description.trim().isEmpty()) {
            throw new InvalidInputException(
                "Course name and description cannot be empty."
            );
        }

        if (durationInWeeks <= 0) {
            throw new InvalidInputException(
                "Duration must be greater than zero."
            );
        }

        course.setCourseName(courseName.trim());
        course.setDescription(description.trim());
        course.setDurationInWeeks(durationInWeeks);
    }
}
