package com.example.seckilling.redis;

/**
 * @author Xiao Yijia
 * @create 2020-09-15 20:48
 */

public interface KeyPrefix {

    int expireSeconds();

    String getPrefix();

}
