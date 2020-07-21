package com.twu.mapper;

import com.twu.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserMapper {

    private static UserMapper userMapper = null;
    private static final String USER_TABLE_NAME = "user";

    public static UserMapper getInstance() {
        if (userMapper == null) {
            userMapper = new UserMapper();
        }
        return userMapper;
    }

    public User findUserByUsername(String username) {
        String query = "SELECT id, username, password, is_admin, number_of_votes FROM " + USER_TABLE_NAME
                + " WHERE username = '" + username + "'";
        ResultSet resultSet = Database.executeQuery(query);
        if (resultSet != null) {
            try {
                if (resultSet.next()) {
                    return new User(resultSet.getString(1), resultSet.getString(2),
                            resultSet.getString(3), resultSet.getBoolean(4),
                            resultSet.getInt(5));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    public List<User> findAllUsers() {
        String query = "SELECT id, username, password, is_admin, number_of_votes FROM " + USER_TABLE_NAME;
        ResultSet resultSet = Database.executeQuery(query);
        List<User> users = new LinkedList<>();
        if (resultSet != null) {
            while (true) {
                try {
                    if (!resultSet.next()) break;
                    users.add(new User(resultSet.getString(1), resultSet.getString(2),
                            resultSet.getString(3), resultSet.getBoolean(4),
                            resultSet.getInt(5)));
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }
            }
        }
        return users;
    }

    public void updateSpecifiedFieldOfUserByUsername(String username, String attribute, String value) {
        String sql = "UPDATE " + USER_TABLE_NAME + " SET " + attribute + " = '" + value + "' WHERE username = '" + username
                + "'";
        Database.execute(sql);
    }

    public void insertUser(String id, String username, String password, boolean isAdmin, int numberOfVotes) {
        String sql = "INSERT INTO " + USER_TABLE_NAME + " (id, username, password, is_admin, number_of_votes) " +
                "VALUES ('" + id + "', '" + username + "', '" + password + "', '" + (isAdmin ? 1 : 0) + "', '" +
                numberOfVotes + "')";
        Database.execute(sql);
    }
}
