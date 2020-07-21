package com.twu.view;

import com.twu.model.Event;

import java.util.List;
import java.util.Scanner;

public class Prompt {

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Require user to choose his/her identity
     * @return User's option
     */
    public static int showWelcomePrompt() {
        System.out.println("欢迎来到热搜排行榜，你是？");
        System.out.println("1. 用户");
        System.out.println("2. 管理员");
        System.out.println("3. 退出");
        return Integer.parseInt(scanner.nextLine());
    }

    /**
     * Get username and return it to controller
     * @return Username
     */
    public static String showLogInPromptOfNormalUser() {
        System.out.println("请输入您的昵称：");
        return scanner.nextLine();
    }

    /**
     * Get username and password and return them to controller
     * @return The array of username and password
     */
    public static String[] showLogInPromptOfAdmin() {
        System.out.println("请输入您的昵称：");
        String username = scanner.nextLine();
        System.out.println("请输入您的密码：");
        String password = scanner.nextLine();
        return new String[] {username, password};
    }

    public static int showOptionsOfNormalUser() {
        System.out.println("1. 查看热搜排行榜");
        System.out.println("2. 给热搜事件投票");
        System.out.println("3. 购买热搜");
        System.out.println("4. 添加热搜");
        System.out.println("5. 退出");
        return Integer.parseInt(scanner.nextLine());
    }

    public static int showOptionsOfAdmin() {
        System.out.println("1. 查看热搜排行榜");
        System.out.println("2. 添加热搜");
        System.out.println("3. 添加超级热搜");
        System.out.println("4. 退出");
        return Integer.parseInt(scanner.nextLine());
    }

    /**
     *
     * @return The event that user wants to add
     */
    public static String showAddEventPrompt() {
        System.out.println("请输入你要添加的热搜事件的名字：");
        return scanner.nextLine();
    }

    /**
     * Prompt user to input the title of event that he/she wants to vote and the amount of votes
     * @param availableNumberOfVotes The number of available votes of current user
     * @return The string array consists of event title and the number of votes
     */
    public static String[] showPromptOfVoting(int availableNumberOfVotes) {
        System.out.println("请输入你要投票的热搜名称：");
        String title = scanner.nextLine();
        System.out.println("请输入你要投票的热搜票数：（你目前还有：" + availableNumberOfVotes + "票）");
        String numberOfVotes = String.valueOf(Integer.parseInt(scanner.nextLine()));
        return new String[] {title, numberOfVotes};
    }

    public static String[] showPromptOfBuyingEventRank() {
        System.out.println("请输入你要购买的热搜名称：");
        String title = scanner.nextLine();
        System.out.println("请输入你要购买的热搜排名：");
        String rank = String.valueOf(Integer.parseInt(scanner.nextLine()));
        System.out.println("请输入你要购买的热搜金额：");
        String value = String.valueOf(Integer.parseInt(scanner.nextLine()));
        return new String[] {title, rank, value};
    }

    public static String showAddSuperEventPrompt() {
        return showAddEventPrompt();
    }

    public static void showRankList(List<Event> events) {
        System.out.println("序号\t标题\t票数");
        for (int i = 0; i < events.size(); i++) {
            System.out.println(i + 1 + "\t" + events.get(i).getTitle() + "\t" + events.get(i).getNumberOfVotes());
        }
    }
}
