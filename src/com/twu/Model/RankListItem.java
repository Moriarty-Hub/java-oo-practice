package com.twu.Model;

public class RankListItem {
    private final int index;
    private Event event;
    private int price;

    public RankListItem(int index, Event event, int price) {
        this.index = index;
        this.event = event;
        this.price = price;
    }

    public int getIndex() {
        return index;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
