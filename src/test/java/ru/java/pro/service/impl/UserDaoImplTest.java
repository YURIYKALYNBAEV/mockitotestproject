package ru.java.pro.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.java.pro.model.User;
import ru.java.pro.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoImplTest {
    private UserRepository userRepository;

    private final User user1 = new User("User1");

    private final User user2 = new User("User2");

    private final User user3 = new User("User3");

    private final User user4 = new User("User4");

    @BeforeEach
    public void init() {
        userRepository = new UserRepository();
    }

    @Test
    @DisplayName("Список пользователей успешно возвращен")
    void shouldReturnAllUsersList() {
        userRepository.addUser(user1);
        userRepository.addUser(user2);

        List<User> expectedUserList = new ArrayList<>();
        expectedUserList.add(user1);
        expectedUserList.add(user2);

        assertEquals(expectedUserList, userRepository.getAllUsers());
    }

    @Test
    @DisplayName("Пользователь с логином, существующем в списке, успешно найден и метод возвращает true")
    void shouldFindUserByLoginIfUserExists() {
        userRepository.addUser(user1);

        Optional<User> expectedUser = Optional.of(user1);

        assertNotNull(userRepository.getUserByLogin(user1.getLogin()));
        assertEquals(expectedUser, userRepository.getUserByLogin(user1.getLogin()));
    }

    @Test
    @DisplayName("Пользователь с логином, не существующем в списке, не найден и метод возвращает null")
    void shouldFindUserByLoginIfUserDoesntExist() {
        userRepository.addUser(user1);

        Optional<User> expectedUser = Optional.of(user2);
        assertNotNull(userRepository.getUserByLogin(user1.getLogin()));
        assertNotEquals(expectedUser, userRepository.getUserByLogin(user1.getLogin()));
    }
}