package com.Revature.Mehrab.JavalinPractice;

import com.Revature.Mehrab.JavalinPractice.courses.CourseController;
import com.Revature.Mehrab.JavalinPractice.courses.H2CourseDao;
import com.Revature.Mehrab.JavalinPractice.students.StudentController;
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
        CourseController courseController = new CourseController(app, H2CourseDao.getInstance());
        StudentController studentController = new StudentController(app);

        app.start(8080);
    }
}
