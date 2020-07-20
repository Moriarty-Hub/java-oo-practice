package com.twu.model;

import java.util.Objects;

public class Event {
    private final String id;
    private final String title;
    private int numberOfVotes;
    private int specifiedRank;
    private int price;
    private boolean isSuperEvent;

    public Event(String id, String title, int numberOfVotes, int specifiedRank, int price, boolean isSuperEvent) {
        this.id = id;
        this.title = title;
        this.numberOfVotes = numberOfVotes;
        this.specifiedRank = specifiedRank;
        this.price = price;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isSuperEvent() {
        return isSuperEvent;
    }

    public void setSuperEvent(boolean superEvent) {
        isSuperEvent = superEvent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return numberOfVotes == event.numberOfVotes &&
                specifiedRank == event.specifiedRank &&
                price == event.price &&
                isSuperEvent == event.isSuperEvent &&
                id.equals(event.id) &&
                title.equals(event.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, numberOfVotes, specifiedRank, price, isSuperEvent);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", numberOfVotes=" + numberOfVotes +
                ", specifiedRank=" + specifiedRank +
                ", price=" + price +
                ", isSuperEvent=" + isSuperEvent +
                '}';
    }
}
