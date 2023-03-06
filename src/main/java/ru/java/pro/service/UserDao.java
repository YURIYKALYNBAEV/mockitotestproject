package ru.java.pro.service;

import ru.java.pro.model.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserDao {
    List<String> findAllUsers();

    void addUser(String login);

    boolean getUserByName(String login);

}
