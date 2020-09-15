package com.example.seckilling.redis;

/**
 * @author Xiao Yijia
 * @create 2020-09-15 20:52
 */
public class UserKey extends BasePrefix {
    private UserKey(String prefix) {
        super(prefix);
    }

    public static UserKey getByID = new UserKey("id");
    public static UserKey getByName = new UserKey("name");
}
