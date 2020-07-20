package com.twu.model;

import java.util.Objects;
import java.util.UUID;

public class User {
    private final String id;
    private final String username;
    private final String password;
    private final boolean isAdmin;
    private int numberOfVotes;

    public User(String id, String username, String password, boolean isAdmin, int numberOfVotes) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.numberOfVotes = numberOfVotes;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public int getNumberOfVotes() {
        return numberOfVotes;
    }

    public void setNumberOfVotes(int numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return isAdmin == user.isAdmin &&
                numberOfVotes == user.numberOfVotes &&
                id.equals(user.id) &&
                username.equals(user.username) &&
                password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, isAdmin, numberOfVotes);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                ", numberOfVotes=" + numberOfVotes +
                '}';
    }
}
