package com.twu;

import java.io.ByteArrayInputStream;

public class MainTest {

    public static void main(String[] args) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(("1\nidolice\n1\n4\n猪肉涨价了\n1\n4\n" +
                "石油降价了\n4\n比特币跌了\n4\n股市崩了\n1\n2\n股市崩了\n11\n2\n股市崩了\n2\n1\n3\n比特币跌了\n1\n1\n1\n3\n" +
                "猪肉涨价了\n1\n1\n3\n猪肉涨价了\n1\n2\n1\n2\n石油降价了\n4\n1\n5\n2\nadmin\nadmin123\n1\n3\n" +
                "这是一个超级热搜\n1\n4\n1\nxiaowang\n1\n2\n这是一个超级热搜\n5\n1\n5\n3\n").getBytes());
        System.setIn(byteArrayInputStream);

        Main.main(null);
    }
}
