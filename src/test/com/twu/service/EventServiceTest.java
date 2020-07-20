package com.twu.service;

import com.twu.mapper.Database;
import com.twu.mapper.EventMapper;
import com.twu.mapper.UserMapper;
import com.twu.model.Event;
import com.twu.model.User;
import com.twu.util.TestData;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EventServiceTest {

    private final EventService eventService = new EventService();

    private static UserMapper userMapper;
    private static EventMapper eventMapper;

    @BeforeAll
    static void setUp() throws SQLException, ClassNotFoundException {
        Database.establishConnection();
        userMapper = UserMapper.getInstance();
        eventMapper = EventMapper.getInstance();
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
    public void testIsEventExist() {
        assertTrue(eventService.isEventExist("黑人的命也是命"));
        assertTrue(eventService.isEventExist("房价上涨"));
        assertFalse(eventService.isEventExist("A股不割韭菜"));
        assertFalse(eventService.isEventExist("房价下跌"));
    }

    @Test
    public void test1CheckAndUpdateTheVotesOfEvent() {
        String username = "dxr";
        int numberOfVotes = 5;
        String invalidTitle = "富强民主文明和谐";
        assertFalse(eventService.checkAndUpdateTheVotesOfEvent(username, numberOfVotes, invalidTitle));
    }

    @Test
    public void test2CheckAndUpdateTheVotesOfEvent() {
        String username = "dxr";
        int numberOfVotes = 20;
        String validTitle = "房价上涨";
        assertFalse(eventService.checkAndUpdateTheVotesOfEvent(username, numberOfVotes, validTitle));
    }

    @Test
    public void test3CheckAndUpdateTheVotesOfEvent() {
        String username = "dxr";
        int numberOfVotes = 6;
        String validTitle = "美股熔断";

        User user = userMapper.findUserByUsername(username);
        Event event = eventMapper.findEventByTitle(validTitle);
        assertEquals(10, user.getNumberOfVotes());
        assertEquals(8, event.getNumberOfVotes());

        assertTrue(eventService.checkAndUpdateTheVotesOfEvent(username, numberOfVotes, validTitle));

        user = userMapper.findUserByUsername(username);
        event = eventMapper.findEventByTitle(validTitle);
        assertEquals(4, user.getNumberOfVotes());
        assertEquals(14, event.getNumberOfVotes());
    }

    @Test
    public void test4CheckAndUpdateTheVotesOfEvent() {
        String username = "dxr";
        int numberOfVotes = 6;
        String validTitleOfSuperEvent = "A股割韭菜";

        User user = userMapper.findUserByUsername(username);
        Event event = eventMapper.findEventByTitle(validTitleOfSuperEvent);
        assertEquals(10, user.getNumberOfVotes());
        assertEquals(5, event.getNumberOfVotes());

        assertTrue(eventService.checkAndUpdateTheVotesOfEvent(username, numberOfVotes, validTitleOfSuperEvent));

        user = userMapper.findUserByUsername(username);
        event = eventMapper.findEventByTitle(validTitleOfSuperEvent);
        assertEquals(4, user.getNumberOfVotes());
        assertEquals(17, event.getNumberOfVotes());
    }

    @Test
    public void test1CheckAndUpdateRankList() {  // The title is not exist
        String title = "房价下跌";
        int rank = 1;
        int value = 50;
        assertFalse(eventService.checkAndUpdateRankList(title, rank, value));
    }

    @Test
    public void test2CheckAndUpdateRankList() {  // The rank is not in valid range
        String title = "A股割韭菜";
        int rank = 0;
        int value = 50;
        assertFalse(eventService.checkAndUpdateRankList(title, rank, value));
    }

    @Test
    public void test3CheckAndUpdateRankList() {  // The rank is not in valid range
        String title = "A股割韭菜";
        int rank = 0;
        int value = 50;
        assertFalse(eventService.checkAndUpdateRankList(title, rank, value));
    }

    @Test
    public void test4CheckAndUpdateRankList() {  // The value is minus
        String title = "A股割韭菜";
        int rank = 6;
        int value = 50;
        assertFalse(eventService.checkAndUpdateRankList(title, rank, value));
    }

    @Test
    public void test5CheckAndUpdateRankList() {  // The value is less than the price of specified rank
        String title = "美股熔断";
        int rank = 1;
        int value = 40;
        assertFalse(eventService.checkAndUpdateRankList(title, rank, value));
    }

    @Test
    public void test6CheckAndUpdateRankList() {  // All arguments are valid
        String title = "美股熔断";
        int rank = 1;
        int value = 80;

        assertTrue(eventService.checkAndUpdateRankList(title, rank, value));

        List<Event> expectedRankList = new LinkedList<>();
        Event event1 = new Event("b945d517-9121-45eb-97b3-4f795fe95020", "美股熔断", 8,
                1, 80, false);
        Event event2 = new Event("190cc0ba-8079-4ea3-b3a8-732dd8df7ee5", "复工复产", 20,
                0, 0, false);
        Event event3 = new Event("6b915d25-0308-4dda-a464-19ba17063fd5", "A股割韭菜", 5,
                0, 0, true);
        Event event4 = new Event("ce5a4158-909c-46fa-9397-8d74b15693b6", "房价上涨", 16,
                3, 20, false);
        expectedRankList.add(event1);
        expectedRankList.add(event2);
        expectedRankList.add(event4);
        expectedRankList.add(event3);

        List<Event> actualRankList = eventService.getRankList();
        assertEquals(expectedRankList.size(), actualRankList.size());
        for (int i = 0; i < expectedRankList.size(); i++) {
            assertEquals(expectedRankList.get(i), actualRankList.get(i));
        }
    }

    @Test
    public void test7CheckAndUpdateRankList() {  // The same title and same rank, but higher value
        String title = "黑人的命也是命";
        int rank = 1;
        int value = 80;

        assertTrue(eventService.checkAndUpdateRankList(title, rank, value));

        List<Event> expectedRankList = new LinkedList<>();
        Event event1 = new Event("b945d517-9121-45eb-97b3-4f795fe95020", "美股熔断", 8,
                0, 0, false);
        Event event2 = new Event("190cc0ba-8079-4ea3-b3a8-732dd8df7ee5", "复工复产", 20,
                0, 0, false);
        Event event3 = new Event("100b1025-4e29-4a53-9a8f-9e3966434987", "黑人的命也是命", 10,
                1, 80, true);
        Event event4 = new Event("6b915d25-0308-4dda-a464-19ba17063fd5", "A股割韭菜", 5,
                0, 0, true);
        Event event5 = new Event("ce5a4158-909c-46fa-9397-8d74b15693b6", "房价上涨", 16,
                3, 20, false);
        expectedRankList.add(event3);
        expectedRankList.add(event2);
        expectedRankList.add(event5);
        expectedRankList.add(event1);
        expectedRankList.add(event4);

        List<Event> actualRankList = eventService.getRankList();
        assertEquals(expectedRankList.size(), actualRankList.size());
        for (int i = 0; i < expectedRankList.size(); i++) {
            assertEquals(expectedRankList.get(i), actualRankList.get(i));
        }
    }
}
