package com.twu.Model;

import java.util.UUID;

public class User {
    private final String id;
    private final String username;
    private final boolean isAdmin;
    private int numberOfVotes;

    private static final int INITIAL_NUMBER_OF_VOTES  = 10;

    public User(String username, boolean isAdmin) {
        id = UUID.randomUUID().toString();
        this.username = username;
        this.isAdmin = isAdmin;
        this.numberOfVotes = INITIAL_NUMBER_OF_VOTES;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
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
}
