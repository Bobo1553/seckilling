package com.example.seckilling.redis;

/**
 * @author Xiao Yijia
 * @create 2020/9/17 11:21
 */

public class SeckillingUserKey extends BasePrefix {

    public static final int TOKEN_EXPIRE = 3600 * 24 * 2;

    public SeckillingUserKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static SeckillingUserKey token = new SeckillingUserKey(TOKEN_EXPIRE, "token");

}
