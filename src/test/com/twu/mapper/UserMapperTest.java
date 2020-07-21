package com.twu.mapper;

import com.twu.model.User;
import com.twu.util.TestData;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserMapperTest {

    private static UserMapper userMapper;

    @BeforeAll
    static void setUp() throws SQLException, ClassNotFoundException {
        Database.establishConnection();
        userMapper = UserMapper.getInstance();
    }

    @AfterAll
    static void cleanUp() {
        Database.releaseResources();
    }

    @BeforeEach
    public void insertTestData() {
        TestData.insertUser();
    }

    @Test
    public void cleanTestData() {
        TestData.cleanUser();
    }

    @Test
    public void testFindAllUsers() {
        User user1 = new User("19be7aba-85dd-4168-ba9d-1cf550d03329", "admin", "admin", true, 10);
        User user2 = new User("cf1bcc2f-a43c-4480-807e-44ebb4165b75", "dxr", "password", false, 10);
        User user3 = new User("227bceb7-8d1b-4b32-b7c6-2fca9fb47ff7", "frank", "password", false, 8);
        User user4 = new User("88b00d41-763b-414d-9dcd-00ecc8c55631", "tom", "password", false, 5);
        User user5 = new User("234416d4-2f6a-4071-b6b9-04c1d6277776", "jack", "password", false, 0);

        List<User> expectedResult = new LinkedList<>();
        expectedResult.add(user1);
        expectedResult.add(user2);
        expectedResult.add(user3);
        expectedResult.add(user4);
        expectedResult.add(user5);

        List<User> actualResult = userMapper.findAllUsers();

        assertEquals(expectedResult.size(), actualResult.size());

        for (int i = 0; i < expectedResult.size(); i++) {
            assertEquals(expectedResult.get(i), actualResult.get(i));
        }
    }

    @Test
    public void testInsertUser() {
        String id = "de7e2576-19a2-48d3-a6c2-4894e28c9781";
        String username = "young";
        String password = "password";
        boolean isAdmin = false;
        int numberOfVotes = 50;
        User expectedUser = new User(id, username, password, isAdmin, numberOfVotes);

        assertNull(userMapper.findUserByUsername(username));
        userMapper.insertUser(id, username, password, isAdmin, numberOfVotes);
        User actualUser = userMapper.findUserByUsername(username);
        assertNotNull(actualUser);
        assertEquals(expectedUser, actualUser);

    }
}
