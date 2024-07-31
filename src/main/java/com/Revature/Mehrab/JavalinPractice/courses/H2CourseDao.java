package com.Revature.Mehrab.JavalinPractice.courses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.Revature.Mehrab.JavalinPractice.util.ConnectionUtil;
import com.Revature.Mehrab.JavalinPractice.util.DAO;

public class H2CourseDao implements DAO<Course>{
    private final Connection connection;

    private static H2CourseDao instance = null;
    private H2CourseDao() throws SQLException {
        this.connection = ConnectionUtil.getConnection();
    }
    public static H2CourseDao getInstance() {
        if (H2CourseDao.instance != null) {
            return H2CourseDao.instance;
        }
        try {
            H2CourseDao.instance = new H2CourseDao();
            return H2CourseDao.instance;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Iterable<Course> getAll() {
        List<Course> courses= new ArrayList<>();
        try {
            String query = "SELECT * FROM courses;";
            Statement stmt = this.connection.createStatement();
            stmt.execute(query);
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                courses.add(new Course(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException ex) {
        }
        return courses;
    }

    @Override
    public Optional<Course> getById(int id) {
        try {
            String query = "SELECT * FROM courses WHERE id = ?;";
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            rs.next();
            return Optional.of(new Course(rs.getInt(1), rs.getString(2)));
        } catch (SQLException ex) {
            return Optional.empty();
        }
    }

    @Override
    public Iterable<String> getAllNames() {
        List<String> names = new ArrayList<>();
        try {
            String query = "SELECT name FROM courses;";
            Statement stmt = this.connection.createStatement();
            stmt.execute(query);
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                names.add(rs.getString(1));
            }
        } catch (SQLException ex) {
        }
        return names;
    }

    @Override
    public String insert(Course course) {
        try {
            String query = "INSERT INTO courses(name) VALUES (?);";
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setString(1, course.getName());
            stmt.execute();
            return "1 row updated.";
        } catch (SQLException ex) {
            return "0 rows updated.";
        }
    }
    
}
