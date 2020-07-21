package com.twu;

import com.twu.mapper.Database;
import com.twu.util.TestData;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.sql.SQLException;

public class MainTest {

    @BeforeAll
    static void insertAdmin() throws SQLException, ClassNotFoundException {
        Database.establishConnection();
        TestData.insertAdmin();
        Database.releaseResources();
    }

    @AfterAll
    static void cleanTestData() throws SQLException, ClassNotFoundException {
        Database.establishConnection();
        TestData.cleanAll();
        Database.releaseResources();
    }

    @Test
    public void testMain() {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(("1\nidolice\n1\n" +  // 以idolice身份登录
                "4\n猪肉涨价了\n1\n4\n石油降价了\n4\n比特币跌了\n4\n股市崩了\n1\n" +  // 添加四个话题
                "2\n股市崩了\n11\n" +  // 投票
                "2\n股市崩了\n2\n1\n" +  // 投票
                "3\n比特币跌了\n1\n1\n1\n" +  // 买热搜
                "3\n猪肉涨价了\n1\n1\n" +  // 买热搜
                "3\n猪肉涨价了\n1\n2\n1\n" +  // 买热搜
                "2\n石油降价了\n4\n1\n5\n" +  //投票
                "2\nadmin\nadmin123\n1\n" +  // 以管理员身份登录
                "3\n这是一个超级热搜\n1\n4\n" +  // 添加超级热搜
                "1\nxiaowang\n1\n" +  // 以xiaowang身份登录
                "2\n这是一个超级热搜\n5\n1\n" +  // 为超级热搜投票
                "5\n3\n")  // 退出系统
                .getBytes());
        System.setIn(byteArrayInputStream);

        Main.main(null);
    }
}
