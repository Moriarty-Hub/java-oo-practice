package com.twu.Model;

import java.util.UUID;

public class Event {
    private final String id;
    private final String title;
    private int numberOfVotes;
    private int specifiedRank;
    private boolean isSuperEvent;

    private static final int INITIAL_NUMBER_OF_VOTES = 0;

    public Event(String title, int specifiedRank, boolean isSuperEvent) {
        id = UUID.randomUUID().toString();
        this.title = title;
        numberOfVotes = INITIAL_NUMBER_OF_VOTES;
        this.specifiedRank = specifiedRank;
        this.isSuperEvent = isSuperEvent;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getNumberOfVotes() {
        return numberOfVotes;
    }

    public void setNumberOfVotes(int numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }

    public int getSpecifiedRank() {
        return specifiedRank;
    }

    public void setSpecifiedRank(int specifiedRank) {
        this.specifiedRank = specifiedRank;
    }

    public boolean isSuperEvent() {
        return isSuperEvent;
    }

    public void setSuperEvent(boolean superEvent) {
        isSuperEvent = superEvent;
    }
}
