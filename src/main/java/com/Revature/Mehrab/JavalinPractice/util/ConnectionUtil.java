package com.Revature.Mehrab.JavalinPractice.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.h2.jdbcx.JdbcDataSource;

public class ConnectionUtil {
    private static final String URL = "jdbc:h2:./h2/db;INIT=runscript from 'init.sql'";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "";

    private static final JdbcDataSource pool = new JdbcDataSource();
    private static Connection connection = null;

    static {
        pool.setUrl(ConnectionUtil.URL);
        pool.setUser(ConnectionUtil.USERNAME);
        pool.setPassword(ConnectionUtil.PASSWORD);
    }

    public static Connection getConnection() {
        if (ConnectionUtil.connection != null) {
            return ConnectionUtil.connection;
        }
        try {
            ConnectionUtil.connection = pool.getConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ConnectionUtil.connection;
    }


}
