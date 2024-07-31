package com.Revature.Mehrab.JavalinPractice.courses;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.Revature.Mehrab.JavalinPractice.util.DAO;

public class EmbeddedCourseDao implements DAO<Course>{
    private final List<Course> courses = Arrays.asList(
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

    @Override
    public Optional<Course> getById(int id) {
        return courses.stream()
            .filter(course -> course.getId() == id)
            .findAny();
    }

    @Override
    public Iterable<String> getAllNames() {
        return courses.stream()
            .map(course -> course.getName())
            .collect(Collectors.toList());
    }

    @Override
    public Iterable<Course> getAll() {
        return this.courses;
    }

    @Override
    public String insert(Course course) {
        this.courses.add(course);
        return "1 row updated";
    }
}
