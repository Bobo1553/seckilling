package com.example.seckilling.service;

import com.example.seckilling.dao.UserDao;
import com.example.seckilling.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Xiao Yijia
 * @create 2020-09-15 10:38
 */

@Service
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getById(int id) {
        return userDao.getById(id);
    }

    @Transactional
    public void transaction() {
        User user1 = new User();
        user1.setId(3);
        user1.setName("Tinker");
        userDao.insert(user1);

        User user2 = new User();
        user2.setId(1);
        user2.setName("Tom");
        userDao.insert(user2);

    }

}
