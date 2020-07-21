package com.twu.service;

import com.twu.mapper.Database;
import com.twu.mapper.UserMapper;
import com.twu.model.User;
import com.twu.util.TestData;
import org.junit.jupiter.api.*;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    private final UserService userService = new UserService();

    private static UserMapper userMapper;

    @BeforeAll
    static void setUp() throws SQLException, ClassNotFoundException {
        Database.establishConnection();
        userMapper = UserMapper.getInstance();
    }

    @AfterAll
    static void CleanUp() {
        Database.releaseResources();
    }

    @BeforeEach
    public void resetDatabase() {
        TestData.insertAll();
    }

    @AfterEach
    public void cleanTestData() {
        TestData.cleanAll();
    }

    @Test
    public void testIsAccountValid() {
        String username1 = "admin";
        String username2 = "dxr";
        String username3 = "visitor";
        String password1 = "admin";
        String password2 = "password";
        String password3 = "123456";
        assertTrue(userService.isAccountValid(username1, password1));
        assertTrue(userService.isAccountValid(username2, password2));
        assertFalse(userService.isAccountValid(username3, password3));
        assertFalse(userService.isAccountValid(username1, password2));
    }

    @Test
    public void testIsUsernameExist() {
        String username1 = "admin";
        String username2 = "frank";
        String username3 = "visitor";
        String username4 = "anonymity";
        assertTrue(userService.isUsernameExist(username1));
        assertTrue(userService.isUsernameExist(username2));
        assertFalse(userService.isUsernameExist(username3));
        assertFalse(userService.isUsernameExist(username4));
    }

    @Test
    public void testCreateNewUser() {
        String username = "visitor";
        assertNull(userMapper.findUserByUsername(username));
        userService.createNewUser(username);
        User user = userMapper.findUserByUsername(username);
        assertNotNull(user);
        assertEquals(username, user.getUsername());
        assertEquals("password", user.getPassword());
        assertFalse(user.isAdmin());
        assertEquals(10, user.getNumberOfVotes());
    }


}
