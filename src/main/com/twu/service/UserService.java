package com.twu.service;

import com.twu.mapper.UserMapper;
import com.twu.model.User;

import java.util.UUID;

public class UserService {

    private static final String DEFAULT_PASSWORD_OF_NORMAL_USER = "password";
    private static final int INITIAL_NUMBER_OF_VOTES = 10;

    private final UserMapper userMapper;

    public UserService() {
        userMapper = new UserMapper();
    }

    public boolean isAccountValid(String username, String password) {
        User user = userMapper.findUserByUsername(username);
        return user != null && user.getPassword().equals(password);
    }

    public boolean isUsernameExist(String username) {
        User user = userMapper.findUserByUsername(username);
        return user != null;
    }

    public void createNewUser(String username) {
        String id = UUID.randomUUID().toString();
        userMapper.insertUser(id, username, DEFAULT_PASSWORD_OF_NORMAL_USER, false, INITIAL_NUMBER_OF_VOTES);
    }

    public User getUserObjectByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }

    public int getNumberOfVotesOfUser(String username) {
        return getUserObjectByUsername(username).getNumberOfVotes();
    }

}
