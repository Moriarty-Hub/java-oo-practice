package com.twu.controller;

import com.twu.mapper.Database;
import com.twu.model.Event;
import com.twu.model.User;
import com.twu.service.EventService;
import com.twu.service.UserService;
import com.twu.view.Prompt;

import java.sql.SQLException;
import java.util.List;

public class CentralController {

    private User currentUser;
    private boolean isAdmin;

    private static final UserService userService = new UserService();
    private static final EventService eventService = new EventService();

    public void start() {
        try {
            Database.establishConnection();
            welcome();
        } catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
            System.out.println("数据库连接失败");
        }
    }

    public void welcome() {
        int userIdentity = Prompt.showWelcomePrompt();
        if (userIdentity == 1) {
            isAdmin = false;
            logIn();
        } else if (userIdentity == 2) {
            isAdmin = true;
            logIn();
        } else if (userIdentity == 3) {
            exit();
        } else {
            System.out.println("无效选项，请重新输入");
            welcome();
        }
    }

    public void logIn() {
        if (isAdmin) {
            String[] account = Prompt.showLogInPromptOfAdmin();
            String username = account[0];
            String password = account[1];
            if (!userService.isAccountValid(username, password)) {
                System.out.println("用户名或密码不正确，登录失败");
                welcome();
            }
            currentUser = userService.getUserObjectByUsername(username);
        } else {
            String username = Prompt.showLogInPromptOfNormalUser();
            if (!userService.isUsernameExist(username)) {
                userService.createNewUser(username);
            }
            currentUser = userService.getUserObjectByUsername(username);
        }
        showOptions();
    }

    public void showOptions() {
        System.out.println("你好，" + currentUser.getUsername() + "，你可以: ");
        if (isAdmin) {
            int option = Prompt.showOptionsOfAdmin();
            switch (option) {
                case 1:
                    viewRankList();
                    break;
                case 2:
                    addEvent();
                    break;
                case 3:
                    addSuperEvent();
                    break;
                case 4:
                    welcome();
                    break;
                default:
                    System.out.println("无效选项，请重新输入");
                    showOptions();
            }
        } else {
            int option = Prompt.showOptionsOfNormalUser();
            switch (option) {
                case 1:
                    viewRankList();
                    break;
                case 2:
                    voteForEvent();
                    break;
                case 3:
                    buyEventRank();
                    break;
                case 4:
                    addEvent();
                    break;
                case 5:
                    welcome();
                    break;
                default:
                    System.out.println("无效选项，请重新输入");
                    showOptions();
            }
        }
    }

    public void viewRankList() {
        List<Event> rankListItems = eventService.getRankList();
        Prompt.showRankList(rankListItems);
        showOptions();
    }

    public void voteForEvent() {
        int numberOfAvailableVotes = userService.getNumberOfVotesOfUser(currentUser.getUsername());
        String[] titleAndNumberOfVotes = Prompt.showPromptOfVoting(numberOfAvailableVotes);
        String title = titleAndNumberOfVotes[0];
        int numberOfVotes = Integer.parseInt(titleAndNumberOfVotes[1]);

        // This method needs to check whether the event exists and the number of votes is valid
        boolean result = eventService.checkAndUpdateTheVotesOfEvent(currentUser.getUsername(), numberOfVotes, title);

        if (!result) {
            System.out.println("投票失败");
        } else {
            System.out.println("投票成功");
        }
        showOptions();
    }

    public void buyEventRank() {
        String[] titleAndRankAndValue = Prompt.showPromptOfBuyingEventRank();
        String title = titleAndRankAndValue[0];
        int rank = Integer.parseInt(titleAndRankAndValue[1]);
        int value = Integer.parseInt(titleAndRankAndValue[2]);
        boolean result = eventService.checkAndUpdateRankList(title, rank, value);
        if (!result) {
            System.out.println("购买失败");
        } else {
            System.out.println("购买成功");
        }
        showOptions();
    }

    public void addEvent() {
        String title = Prompt.showAddEventPrompt();
        boolean isEventExist = eventService.isEventExist(title);
        if (isEventExist) {
            System.out.println("添加失败，事件已存在");
        } else {
            eventService.addNewEvent(title);
            System.out.println("添加成功");
        }
        showOptions();
    }

    public void addSuperEvent() {
        String title = Prompt.showAddSuperEventPrompt();
        boolean isEventExist = eventService.isEventExist(title);
        if (isEventExist) {
            System.out.println("添加失败，事件已存在");
        } else {
            eventService.addNewSuperEvent(title);
            System.out.println("添加成功");
        }
        showOptions();
    }

    public void exit() {
        userService.saveUserIntoDatabase();
        eventService.saveEventIntoDatabase();
        Database.releaseResources();
    }
}
