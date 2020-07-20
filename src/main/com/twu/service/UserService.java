package com.twu.service;

import com.twu.model.User;

public class UserService {

    public UserService() {
        loadUserFromDatabase();
    }

    private void loadUserFromDatabase() {

    }

    public boolean isAccountValid(String username, String password) {

        return false;
    }

    public boolean isUsernameExist(String username) {

        return false;
    }

    public void createNewUser(String username) {

    }

    public User getUserObjectByUsername(String username) {

        return null;
    }

    public int getNumberOfVotesOfUser(String username) {

        return 0;
    }

    public void saveUserIntoDatabase() {

    }

}
