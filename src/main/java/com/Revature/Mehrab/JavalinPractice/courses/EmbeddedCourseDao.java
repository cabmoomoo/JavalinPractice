package com.Revature.Mehrab.JavalinPractice.courses;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EmbeddedCourseDao {
    private List<Course> courses = Arrays.asList(
        new Course(1, "Biology"),
        new Course(2, "Math"),
        new Course(3, "English")
    );

    private static EmbeddedCourseDao embeddedCourseDao = null;

    private EmbeddedCourseDao() {}

    static EmbeddedCourseDao instance() {
        if(embeddedCourseDao == null) {
            embeddedCourseDao = new EmbeddedCourseDao();
        }
        return embeddedCourseDao;
    }

    Optional<Course> getCourseById(int id) {
        return courses.stream()
            .filter(course -> course.getId() == id)
            .findAny();
    }

    Iterable<String> getAllCourseNames() {
        return courses.stream()
            .map(course -> course.getName())
            .collect(Collectors.toList());
    }
}
