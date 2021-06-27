package com.example.seckilling.util;

import java.util.UUID;

/**
 * @author Xiao Yijia
 * @create 2020/9/17 11:16
 */

public class UUIDUtil {

    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
