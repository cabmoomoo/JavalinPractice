package com.Revature.Mehrab.JavalinPractice.students;

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

public class H2StudentDao implements DAO<Student>{
    private final Connection connection;

    private static H2StudentDao instance = null;
    private H2StudentDao() throws SQLException {
        this.connection = ConnectionUtil.getConnection();
    }
    public static H2StudentDao getInstance() {
        if (H2StudentDao.instance != null) {
            return H2StudentDao.instance;
        }
        try {
            H2StudentDao.instance = new H2StudentDao();
            return H2StudentDao.instance;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Iterable<Student> getAll() {
        List<Student> students= new ArrayList<>();
        try {
            String query = "SELECT * FROM students;";
            Statement stmt = this.connection.createStatement();
            stmt.execute(query);
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                students.add(new Student(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException ex) {
        }
        return students;
    }

    @Override
    public Optional<Student> getById(int id) {
        try {
            String query = "SELECT * FROM students WHERE id = ?;";
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            rs.next();
            return Optional.of(new Student(rs.getInt(1), rs.getString(2)));
        } catch (SQLException ex) {
            return Optional.empty();
        }
    }

    @Override
    public Iterable<String> getAllNames() {
        List<String> names = new ArrayList<>();
        try {
            String query = "SELECT name FROM students;";
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
    public String insert(Student course) {
        try {
            String query = "INSERT INTO students(name) VALUES (?);";
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setString(1, course.getName());
            stmt.execute();
            return "1 row updated.";
        } catch (SQLException ex) {
            return "0 rows updated.";
        }
    }
    
}
