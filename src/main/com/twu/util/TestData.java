package com.twu.util;

import com.twu.mapper.Database;

import static org.junit.jupiter.api.Assertions.*;

public class TestData {

    private static final String USER_TABLE_NAME = "user_test";
    private static final String EVENT_TABLE_NAME = "event_test";

    public static void insertAll() {
        insertUser();
        insertEvent();
    }

    public static void insertUser() {
        String insertUser1 = "INSERT INTO " + USER_TABLE_NAME + " (id, username, password, is_admin, number_of_votes) " +
                "VALUES ('19be7aba-85dd-4168-ba9d-1cf550d03329', 'admin', 'admin', '1', '10')";
        String insertUser2 = "INSERT INTO " + USER_TABLE_NAME + " (id, username, password, is_admin, number_of_votes) " +
                "VALUES ('cf1bcc2f-a43c-4480-807e-44ebb4165b75', 'dxr', 'password', '0', '10')";
        String insertUser3 = "INSERT INTO " + USER_TABLE_NAME + " (id, username, password, is_admin, number_of_votes) " +
                "VALUES ('227bceb7-8d1b-4b32-b7c6-2fca9fb47ff7', 'frank', 'password', '0', '8')";
        String insertUser4 = "INSERT INTO " + USER_TABLE_NAME + " (id, username, password, is_admin, number_of_votes) " +
                "VALUES ('88b00d41-763b-414d-9dcd-00ecc8c55631', 'tom', 'password', '0', '5')";
        String insertUser5 = "INSERT INTO " + USER_TABLE_NAME + " (id, username, password, is_admin, number_of_votes) " +
                "VALUES ('234416d4-2f6a-4071-b6b9-04c1d6277776', 'jack', 'password', '0', '0')";

        assertFalse(Database.execute(insertUser1));
        assertFalse(Database.execute(insertUser2));
        assertFalse(Database.execute(insertUser3));
        assertFalse(Database.execute(insertUser4));
        assertFalse(Database.execute(insertUser5));
    }

    public static void insertAdmin() {
        String insertUser = "INSERT INTO " + USER_TABLE_NAME + " (id, username, password, is_admin, number_of_votes) " +
                "VALUES ('19be7aba-85dd-4168-ba9d-1cf550d03329', 'admin', 'admin123', '1', '10')";
        assertFalse(Database.execute(insertUser));
    }

    public static void insertEvent() {
        String event1 = "INSERT INTO " + EVENT_TABLE_NAME + " (id, title, number_of_votes, specified_rank, price, " +
                "is_super_event) VALUES('b945d517-9121-45eb-97b3-4f795fe95020', '美股熔断', '8', '0', '0', '0')";
        String event2 = "INSERT INTO " + EVENT_TABLE_NAME + " (id, title, number_of_votes, specified_rank, price, " +
                "is_super_event) VALUES('190cc0ba-8079-4ea3-b3a8-732dd8df7ee5', '复工复产', '20', '0', '0', '0')";
        String event3 = "INSERT INTO " + EVENT_TABLE_NAME + " (id, title, number_of_votes, specified_rank, price, " +
                "is_super_event) VALUES('100b1025-4e29-4a53-9a8f-9e3966434987', '黑人的命也是命', '10', '1', '50', '1')";
        String event4 = "INSERT INTO " + EVENT_TABLE_NAME + " (id, title, number_of_votes, specified_rank, price, " +
                "is_super_event) VALUES('6b915d25-0308-4dda-a464-19ba17063fd5', 'A股割韭菜', '5', '0', '0', '1')";
        String event5 = "INSERT INTO " + EVENT_TABLE_NAME + " (id, title, number_of_votes, specified_rank, price, " +
                "is_super_event) VALUES('ce5a4158-909c-46fa-9397-8d74b15693b6', '房价上涨', '16', '3', '20', '0')";

        assertFalse(Database.execute(event1));
        assertFalse(Database.execute(event2));
        assertFalse(Database.execute(event3));
        assertFalse(Database.execute(event4));
        assertFalse(Database.execute(event5));
    }

    public static void cleanAll() {
        cleanUser();
        cleanEvent();
    }

    public static void cleanUser() {
        String deleteAllUsers = "DELETE FROM " + USER_TABLE_NAME;
        assertFalse(Database.execute(deleteAllUsers));
    }

    public static void cleanEvent() {
        String deleteAllEvents = "DELETE FROM " + EVENT_TABLE_NAME;
        assertFalse(Database.execute(deleteAllEvents));
    }
}
