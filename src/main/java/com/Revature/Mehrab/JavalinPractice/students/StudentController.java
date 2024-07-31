package com.Revature.Mehrab.JavalinPractice.students;

import com.Revature.Mehrab.JavalinPractice.App;
import com.Revature.Mehrab.JavalinPractice.util.DAO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.javalin.Javalin;
import io.javalin.http.Context;

public class StudentController {
    private final DAO<Student> studentDao;

    public StudentController(Javalin app) {
        this.studentDao = H2StudentDao.getInstance();
        app.get("/students", this::getAllStudents);
        app.post("/students", this::postStudent);
    }

    public void getAllStudents(Context ctx) {
        Iterable<Student> students = this.studentDao.getAll();
        ctx.json(students);
    }

    public void postStudent(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = App.getMapper();
        Student student;
        try {
            student = mapper.readValue(ctx.body(), Student.class);
        } catch (JsonProcessingException ex) {
            student = new Student();
            student.setName(ctx.body());
        }
        String result = this.studentDao.insert(student);
        ctx.result(result);
    }
}
