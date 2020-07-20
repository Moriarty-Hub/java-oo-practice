package com.twu.mapper;

import com.twu.model.Event;
import com.twu.util.TestData;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EventMapperTest {

    private static EventMapper eventMapper;
    private static final List<Event> sampleEventList = new LinkedList<>();

    @BeforeAll
    static void setUp() throws SQLException, ClassNotFoundException {
        Database.establishConnection();
        eventMapper = EventMapper.getInstance();
    }

    @AfterAll
    static void cleanUp() {
        TestData.cleanEvent();
        Database.releaseResources();
    }

    @BeforeEach
    public void insertTestData() {
        TestData.insertEvent();

        sampleEventList.add(new Event("b945d517-9121-45eb-97b3-4f795fe95020", "美股熔断", 8,
                0, 0, false));
        sampleEventList.add(new Event("190cc0ba-8079-4ea3-b3a8-732dd8df7ee5", "复工复产", 20,
                0, 0, false));
        sampleEventList.add(new Event("100b1025-4e29-4a53-9a8f-9e3966434987", "黑人的命也是命", 10,
                1, 50, true));
        sampleEventList.add(new Event("6b915d25-0308-4dda-a464-19ba17063fd5", "A股割韭菜", 5,
                0, 0, true));
        sampleEventList.add(new Event("ce5a4158-909c-46fa-9397-8d74b15693b6", "房价上涨", 16,
                3, 20, false));
    }

    @AfterEach
    public void cleanTestData() {
        TestData.cleanEvent();
        sampleEventList.clear();
    }

    @Test
    public void testFindEventByTitle() {
        Event event1 = new Event("b945d517-9121-45eb-97b3-4f795fe95020", "美股熔断", 8,
                0, 0, false);
        Event event2 = new Event("6b915d25-0308-4dda-a464-19ba17063fd5", "A股割韭菜", 5,
                0, 0, true);
        assertEquals(event1, eventMapper.findEventByTitle("美股熔断"));
        assertEquals(event2, eventMapper.findEventByTitle("A股割韭菜"));
        assertNull(eventMapper.findEventByTitle("村里通网了"));
        assertNull(eventMapper.findEventByTitle("北京申奥成功"));
    }

    @Test
    public void testFindAllEvents() {
        List<Event> actualResult = eventMapper.findAllEvents();
        assertEquals(sampleEventList.size(), actualResult.size());
        for (int i = 0; i < sampleEventList.size(); i++) {
            assertEquals(sampleEventList.get(i), actualResult.get(i));
        }
    }

    @Test
    public void test1DeleteEventByTitle() {
        String validTitle1 = "复工复产";
        String validTitle2 = "美股熔断";

        eventMapper.deleteEventByTitle(validTitle1);
        sampleEventList.remove(1);
        List<Event> actualEventList1 = eventMapper.findAllEvents();
        assertEquals(sampleEventList.size(), actualEventList1.size());
        for (int i = 0; i < sampleEventList.size(); i++) {
            assertEquals(sampleEventList.get(i), actualEventList1.get(i));
        }

        eventMapper.deleteEventByTitle(validTitle2);
        sampleEventList.remove(0);
        List<Event> actualEventList2 = eventMapper.findAllEvents();
        assertEquals(sampleEventList.size(), actualEventList2.size());
        for (int i = 0; i < sampleEventList.size(); i++) {
            assertEquals(sampleEventList.get(i), actualEventList2.get(i));
        }
    }

    @Test
    public void test2DeleteEventByTitle() {
        String invalidTitle1 = "美股暴涨";
        String invalidTitle2 = "房价下跌";

        eventMapper.deleteEventByTitle(invalidTitle1);
        List<Event> actualEventList1 = eventMapper.findAllEvents();
        assertEquals(sampleEventList.size(), actualEventList1.size());

        eventMapper.deleteEventByTitle(invalidTitle2);
        List<Event> actualEventList2 = eventMapper.findAllEvents();
        assertEquals(sampleEventList.size(), actualEventList2.size());
    }

    @Test
    public void testUpdateSpecifiedFieldOfEventByTitle() {
        String title = "复工复产";
        String attribute = "number_of_votes";
        String value = "12";

        //sampleEventList.forEach(System.out::println);

        eventMapper.updateSpecifiedFieldOfEventByTitle(title, attribute, value);
        List<Event> actualEventList = eventMapper.findAllEvents();
        sampleEventList.get(1).setNumberOfVotes(12);
        assertEquals(sampleEventList.size(), actualEventList.size());
        for (int i = 0; i < sampleEventList.size(); i++) {
            assertEquals(sampleEventList.get(i), actualEventList.get(i));
        }
    }
}
