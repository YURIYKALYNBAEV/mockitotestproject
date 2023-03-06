package ru.java.pro.service.impl;

import ru.java.pro.model.User;
import ru.java.pro.service.UserDao;
import ru.java.pro.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean checkUserExist(User user) {
        if (userDao.getUserByName(user.getLogin())) {
            return true;
        }
        return false;
    }
}
