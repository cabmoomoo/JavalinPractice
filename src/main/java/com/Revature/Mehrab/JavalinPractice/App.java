package com.Revature.Mehrab.JavalinPractice;

import com.Revature.Mehrab.JavalinPractice.courses.CourseController;
import com.Revature.Mehrab.JavalinPractice.courses.H3CourseDao;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

import io.javalin.Javalin;

public class App {
    private static final ObjectMapper mapper = new ObjectMapper();
    static {
        App.mapper.registerModule(new Jdk8Module());
    }
    public static ObjectMapper getMapper() {
        return App.mapper;
    }

    public static void main(String[] args) {
        Javalin app = Javalin.create();

        app.get("/hello", ctx -> ctx.html("Hello from Javalin!"));

        // CourseController courseController = new CourseController();
        CourseController courseController = new CourseController(H3CourseDao.getInstance());
        app.get("/courses", courseController::getAllCourses);
        app.get("/courses/{id}", courseController::getById);

        app.start(8080);
    }
}
