package com.example.seckilling.redis;

/**
 * @author Xiao Yijia
 * @create 2020-09-15 20:52
 */
public class OrderKey extends BasePrefix {
    public OrderKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
}
