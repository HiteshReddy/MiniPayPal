package com.minipaypal.manager.user;

import java.util.Random;

public class ManagerUtils {

    public static int generateId() {
        Random r = new Random(System.currentTimeMillis());
        return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
    }
}
