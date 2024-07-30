package com.Revature.Mehrab.JavalinPractice.courses;

import java.util.Optional;

import io.javalin.http.Handler;

public class CourseController {
    public static Handler fetchAllCourseNames = ctx -> {
        EmbeddedCourseDao dao = EmbeddedCourseDao.instance();
        Iterable<String> allCourses = dao.getAllCourseNames();
        ctx.json(allCourses);
    };

    public static Handler fetchByCourseId = ctx -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        EmbeddedCourseDao dao = EmbeddedCourseDao.instance();
        Optional<Course> course = dao.getCourseById(id);
        ctx.json(course);
    };
}
