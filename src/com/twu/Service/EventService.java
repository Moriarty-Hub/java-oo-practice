package com.twu.Service;

import com.twu.Model.RankListItem;

import java.util.LinkedList;
import java.util.List;

public class EventService {

    public List<RankListItem> getRankList() {
        List<RankListItem> rankListItems = new LinkedList<>();

        return rankListItems;
    }

    public boolean isEventExist(String titleOfEvent) {

        return false;
    }

    public boolean checkAndUpdateTheVotesOfEvent(String username, int availableNumberOfVotes,
                                                 int numberOfVotes, String title) {

        return false;
    }

    public boolean checkAndUpdateRankList(String title, int rank, int value) {

        return false;
    }

    public void addNewEvent(String title) {

    }

    public void addNewSuperEvent(String title) {

    }
}
