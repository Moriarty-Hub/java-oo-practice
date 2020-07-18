package com.twu.View;

import com.twu.Model.RankListItem;

import java.util.List;

public class Prompt {

    /**
     * Require user to choose his/her identity
     * @return User's option
     */
    public static int showWelcomePrompt() {

        return 0;
    }

    /**
     * Get username and return it to controller
     * @return Username
     */
    public static String showLogInPromptOfNormalUser() {

        return null;
    }

    /**
     * Get username and password and return them to controller
     * @return The array of username and password
     */
    public static String[] showLogInPromptOfAdmin() {

        return null;
    }

    public static void showOptionsOfNormalUser() {

    }

    public static void showOptionsOfAdmin() {

    }

    public static String showAddEventPrompt() {

        return null;
    }

    /**
     * Prompt user to input the title of event that he/she wants to vote and the amount of votes
     * @param availableNumberOfVotes The number of available votes of current user
     * @return The string array consists of event title and the number of votes
     */
    public static String[] showPromptOfVoting(int availableNumberOfVotes) {

        return null;
    }

    public static String[] showPromptOfBuyingEventRank() {

        return null;
    }

    public static String showAddSuperEventPrompt() {

        return null;
    }

    public static void showRankList(List<RankListItem> rankListItems) {
        System.out.println("序号\t标题\t票数");
        for (RankListItem rankListItem : rankListItems) {
            System.out.println(rankListItem.getIndex() + "\t" + rankListItem.getEvent().getTitle() + "\t"
                    + rankListItem.getEvent().getNumberOfVotes());
        }
    }
}
