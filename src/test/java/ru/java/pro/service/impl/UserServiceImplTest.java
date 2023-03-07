package ru.java.pro.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.java.pro.model.User;
import ru.java.pro.repository.UserRepository;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.atMostOnce;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    private final User user1 = new User("User1");
    private UserRepository userRepository;
    @Mock
    private UserDaoImpl repositoryMock;
    @InjectMocks
    private UserServiceImpl out;

    @BeforeEach
    public void init() {
        userRepository = new UserRepository();
    }

    @Test
    @DisplayName("Пользователь с логином, существующем в списке, успешно найден и метод возвращает true")
    void shouldSearchUserByValidLoginShouldReturnsTrue() {
        userRepository.addUser(user1);
        when(repositoryMock.getUserByName(user1.getLogin()))
                .thenReturn(true);

        Assertions.assertEquals(true, out.checkUserExist(user1));

        verify(repositoryMock, atMostOnce()).getUserByName(anyString());
    }
    @Test
    @DisplayName("Пользователь с логином, не существующем в списке, не найден и метод возвращает false")
    void shouldSearchUserByValidLoginShouldReturnsFalse() {
        userRepository.addUser(user1);
        when(repositoryMock.getUserByName(anyString()))
                .thenReturn(false);

        Assertions.assertEquals(false, out.checkUserExist(new User("")));

        verify(repositoryMock, atMostOnce()).getUserByName(anyString());
    }

}
