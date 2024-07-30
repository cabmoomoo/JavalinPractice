package com.Revature.Mehrab.JavalinPractice;

import com.Revature.Mehrab.JavalinPractice.courses.CourseController;

import io.javalin.Javalin;

public class App {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(8080);
        app.get("/hello", ctx -> ctx.html("Hello from Javalin!"));
        app.get("/courses", CourseController.fetchAllCourseNames);
        app.get("/courses/{id}", CourseController.fetchByCourseId);
    }
}
