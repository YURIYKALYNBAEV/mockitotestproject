package ru.java.pro.service.impl;

import ru.java.pro.repository.UserRepository;
import ru.java.pro.service.UserDao;
import ru.java.pro.exception.UserNonUniqueException;
import ru.java.pro.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserDaoImpl implements UserDao {
    private UserRepository userRepository;

    public void UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<String> findAllUsers() {
        return userRepository.getAllUsers().stream()
                .map(User::getLogin)
                .collect(Collectors.toList());
    }

    @Override
    public void addUser(String login) {
        if (login == null || login.isBlank() || login.isEmpty()) {
            throw new IllegalArgumentException("Логин пользователя не должен быть пустым");
        }

        User user = new User(login);

        boolean isUserExists = userRepository.getAllUsers().stream()
                .anyMatch(e -> e.equals(user));

        if (isUserExists) {
            try {
                throw new UserNonUniqueException("Пользователь с таким логином уже существует");
            } catch (UserNonUniqueException e) {
                throw new RuntimeException(e);
            }
        }

        userRepository.addUser(user);

    }

    @Override
    public boolean getUserByName(String login) {
        return userRepository.getUserByLogin(login).isPresent();
    }
}
