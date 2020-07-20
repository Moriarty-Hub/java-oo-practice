package com.twu.mapper;

import java.sql.*;

public class Database {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost/java-oo-practice?serverTimezone=Asia/Shanghai";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static Connection connection = null;
    private static Statement statement = null;

    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    private static Statement getStatement() throws SQLException {
        return connection.createStatement();
    }

    public static void establishConnection() throws SQLException, ClassNotFoundException {
        connection = getConnection();
        statement = getStatement();
    }

    public static ResultSet executeQuery(String query) {
        try {
            return statement.executeQuery(query);
        } catch (SQLException throwable) {
            return null;
        }
    }

    public static boolean execute(String sql) {
        try {
            return statement.execute(sql);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return false;
        }
    }

    public static void releaseResources() {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Database.establishConnection();
        execute("INSERT INTO user_test (id, username, password, is_admin, number_of_votes) " +
                "VALUES ('19be7aba-85dd-4168-ba9d-1cf550d03329', 'admin', 'admin', '1', '10')");
        Database.releaseResources();
    }
}
