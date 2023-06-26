package com.solvd.twitter.service;

import com.solvd.twitter.domain.exception.ResourceAlreadyExistsException;
import com.solvd.twitter.domain.exception.ResourceDoesNotExistException;
import com.solvd.twitter.domain.user.User;
import com.solvd.twitter.repository.UserRepository;
import com.solvd.twitter.service.impl.UserServiceImpl;
import com.solvd.twitter.service.factory.TwitterFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @BeforeEach
    void setUp(){
        userService = new UserServiceImpl(userRepository, bCryptPasswordEncoder);
    }

    @Test
    void verifyCreatePassedTest() {
        User user = TwitterFactory.generateUser();
        String userId = "1";
        user.setId(null);
        Mockito.when(userRepository.existsByEmail(Mockito.anyString()))
                .thenReturn(false);
        Mockito.doAnswer(invocation -> {
                User userCreated = invocation.getArgument(0);
                userCreated.setId(userId);
                return user;
        }).when(userRepository).save(user);
        User userCreated = userService.create(user);
        Assertions.assertEquals(user, userCreated,
                "Assert that user and userCreated are equals");
        Mockito.verify(userRepository, Mockito.times(1))
                .existsByEmail(user.getEmail());
        Mockito.verify(userRepository, Mockito.times(1))
                .save(user);
    }

    @Test
    void verifyCreateFailedTest() {
        User user = TwitterFactory.generateUser();
        String userId = "1";
        Mockito.when(userRepository.existsByEmail(Mockito.anyString()))
                .thenReturn(true);
        Assertions.assertThrows(ResourceAlreadyExistsException.class,
                ()-> userService.create(user),
                "Assert ResourceAlreadyExistsException");
        Mockito.verify(userRepository, Mockito.times(1))
                .existsByEmail(user.getEmail());
    }

    @Test
    void verifyFindByIdPassedTest() {
        User user = TwitterFactory.generateUser();
        String userId = "1";
        Mockito.when(userRepository.findById(Mockito.anyString()))
                .thenReturn(Optional.of(user));
        User userFounded = userService.findById(userId);
        Assertions.assertEquals(user, userFounded,
                "Assert that user and userFounded are equals");
        Mockito.verify(userRepository, Mockito.times(1))
                .findById(userId);
    }

    @Test
    void verifyFindByIdFailedTest() {
        String userId = "1";
        Mockito.when(userRepository.findById(Mockito.anyString()))
                .thenReturn(Optional.empty());
        Assertions.assertThrows(ResourceDoesNotExistException.class,
                () -> userService.findById(userId),
                "Assert ResourceDoesNotExistException");
        Mockito.verify(userRepository, Mockito.times(1))
                .findById(userId);
    }

    @Test
    void verifyUpdateTest() {
        User user = TwitterFactory.generateUser();
        String userId = "1";
        Mockito.when(userRepository.update(Mockito.any())).thenReturn(user);
        User userUpdated = userService.update(user);
        Assertions.assertEquals(userId, userUpdated.getId(),
                "Assert that userId and userUpdated id are equals");
        Mockito.verify(userRepository, Mockito.times(1))
                .update(user);
    }

    @Test
    void verifyDeleteByIdTest() {
        String userId = "1";
        userService.deleteById(userId);
        Mockito.verify(userRepository, Mockito.times(1))
                .deleteById(userId);
    }

}
