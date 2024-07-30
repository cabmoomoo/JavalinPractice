package com.Revature.Mehrab.JavalinPractice.courses;

import java.util.Optional;

import com.Revature.Mehrab.JavalinPractice.App;
import com.Revature.Mehrab.JavalinPractice.util.DAO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.javalin.http.Context;

public class CourseController {
    private final DAO<Course> courseDao;

    public CourseController() {
        this.courseDao = EmbeddedCourseDao.instance();
    }

    public CourseController(DAO<Course> courseDao) {
        this.courseDao = courseDao;
    }

    public void getAllCourses(Context ctx) {
        Iterable<Course> allCourses = this.courseDao.getAll();
        ctx.json(allCourses);
    }

    // public static Handler fetchAllCourseNames = ctx -> {
    //     EmbeddedCourseDao dao = EmbeddedCourseDao.instance();
    //     Iterable<String> allCourses = dao.getAllCourseNames();
    //     ctx.json(allCourses);
    // };

    public void getAllCourseNames(Context ctx) {
        Iterable<String> allCourseNames = this.courseDao.getAllNames();
        ctx.json(allCourseNames);
    }

    // public static Handler fetchByCourseId = ctx -> {
    //     int id = Integer.parseInt(ctx.pathParam("id"));
    //     EmbeddedCourseDao dao = EmbeddedCourseDao.instance();
    //     Optional<Course> course = dao.getCourseById(id);
    //     ctx.json(course);
    // };

    public void getById(Context ctx) throws JsonProcessingException {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Optional<Course> course = this.courseDao.getById(id);
        ObjectMapper mapper = App.getMapper();
        ctx.json(mapper.writeValueAsString(course));
    }
}
