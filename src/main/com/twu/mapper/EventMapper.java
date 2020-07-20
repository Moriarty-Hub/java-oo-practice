package com.twu.mapper;

import com.twu.model.Event;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class EventMapper {

    private static EventMapper eventMapper = null;
    private static final String EVENT_TABLE_NAME = "event_test";

    public static EventMapper getInstance() {
        if (eventMapper == null) {
            eventMapper = new EventMapper();
        }
        return eventMapper;
    }

    public Event findEventByTitle(String title) {
        String query = "SELECT id, title, number_of_votes, specified_rank, price, is_super_event FROM "
                + EVENT_TABLE_NAME + " WHERE title = '" + title + "'";
        ResultSet resultSet = Database.executeQuery(query);
        if (resultSet != null) {
            try {
                if (resultSet.next()) {
                    return new Event(resultSet.getString(1), resultSet.getString(2),
                            resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5),
                            resultSet.getBoolean(6));
                }
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
        return null;
    }

    public List<Event> findAllEvents() {
        String query = "SELECT id, title, number_of_votes, specified_rank, price, is_super_event FROM " + EVENT_TABLE_NAME;
        List<Event> events = new LinkedList<>();
        ResultSet resultSet = Database.executeQuery(query);
        if (resultSet != null) {
            while (true) {
                try {
                    if (!resultSet.next()) break;
                    events.add(new Event(resultSet.getString(1), resultSet.getString(2),
                            resultSet.getInt(3), resultSet.getInt(4),
                            resultSet.getInt(5), resultSet.getBoolean(6)));
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }
            }
        }
        return events;
    }

    public void deleteEventByTitle(String title) {
        String sql = "DELETE FROM " + EVENT_TABLE_NAME + " WHERE title = '" + title + "'";
        Database.execute(sql);
    }

    public void updateSpecifiedFieldOfEventByTitle(String title, String attribute, String value) {
        String sql = "UPDATE " + EVENT_TABLE_NAME + " SET " + attribute + " = '" + value + "' WHERE title = '" + title
                + "'";
        Database.execute(sql);
    }
}
