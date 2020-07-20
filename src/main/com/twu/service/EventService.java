package com.twu.service;

import com.twu.mapper.EventMapper;
import com.twu.mapper.UserMapper;
import com.twu.model.Event;
import com.twu.model.User;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class EventService {

    private static final int INITIAL_NUMBER_OF_VOTES = 0;
    private static final int INITIAL_SPECIFIED_RANK = 0;
    private static final int INITIAL_PRICE = 0;

    private final UserMapper userMapper;
    private final EventMapper eventMapper;

    public EventService() {
        userMapper = UserMapper.getInstance();
        eventMapper = EventMapper.getInstance();
    }

    public List<Event> getRankList() {
        List<Event> rankList = new LinkedList<>();
        List<Event> originalRankList = eventMapper.findAllEvents();
        for (int i = 0; i < originalRankList.size(); i++) {
            rankList.add(null);
        }
        originalRankList.stream().filter(event -> event.getSpecifiedRank() != 0)
                .forEach(event -> rankList.set(event.getSpecifiedRank() - 1, event));
        List<Event> sortedWithoutSpecifiedRankEvent = originalRankList.stream()
                .filter(event -> event.getSpecifiedRank() == 0)
                .sorted((event1, event2) -> Integer.compare(event2.getNumberOfVotes(), event1.getNumberOfVotes()))
                .collect(Collectors.toList());

        for (int i = 0, j = 0; i < rankList.size() && j < sortedWithoutSpecifiedRankEvent.size(); i++) {
            if (rankList.get(i) == null) {
                rankList.set(i, sortedWithoutSpecifiedRankEvent.get(j++));
            }
        }
        return rankList;
    }

    public boolean isEventExist(String titleOfEvent) {
        return eventMapper.findEventByTitle(titleOfEvent) != null;
    }

    /**
     * This method will checks whether the title exists, and the number of votes is valid
     * @param username Username of current user
     * @param numberOfVotes The number of votes that user wants to vote for the title
     * @param title The title that user wants to vote for
     * @return The validation of all arguments
     */
    public boolean checkAndUpdateTheVotesOfEvent(String username, int numberOfVotes, String title) {
        if (!isEventExist(title)) {
            return false;
        }
        User user = userMapper.findUserByUsername(username);
        Event event = eventMapper.findEventByTitle(title);
        if (user.getNumberOfVotes() < numberOfVotes) {
            return false;
        }
        userMapper.updateSpecifiedFieldOfUserByUsername(username, "number_of_votes",
                String.valueOf(user.getNumberOfVotes() - numberOfVotes));
        if (event.isSuperEvent()) {
            eventMapper.updateSpecifiedFieldOfEventByTitle(title, "number_of_votes",
                    String.valueOf(event.getNumberOfVotes() + (2 * numberOfVotes)));
        } else {
            eventMapper.updateSpecifiedFieldOfEventByTitle(title, "number_of_votes",
                    String.valueOf(event.getNumberOfVotes() + numberOfVotes));
        }
        return true;
    }

    public boolean checkAndUpdateRankList(String title, int rank, int value) {
        List<Event> rankList = getRankList();
        if (!isEventExist(title)) {
            return false;
        }
        if (rank < 1 || rank > rankList.size()) {
            return false;
        }
        if (value <= rankList.get(rank - 1).getPrice()) {
            return false;
        }
        if (!title.equals(rankList.get(rank - 1).getTitle())) {
            eventMapper.deleteEventByTitle(rankList.get(rank - 1).getTitle());
            eventMapper.updateSpecifiedFieldOfEventByTitle(title, "specified_rank", Integer.toString(rank));
        }
        eventMapper.updateSpecifiedFieldOfEventByTitle(title, "price", Integer.toString(value));
        return true;
    }

    public void addNewEvent(String title) {
        String id = UUID.randomUUID().toString();
        eventMapper.insertEvent(id, title, INITIAL_NUMBER_OF_VOTES, INITIAL_SPECIFIED_RANK, INITIAL_PRICE, false);
    }

    public void addNewSuperEvent(String title) {
        String id = UUID.randomUUID().toString();
        eventMapper.insertEvent(id, title, INITIAL_NUMBER_OF_VOTES, INITIAL_SPECIFIED_RANK, INITIAL_PRICE, true);
    }

    public void saveEventIntoDatabase() {

    }

}
