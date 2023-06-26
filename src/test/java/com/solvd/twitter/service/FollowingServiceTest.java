package com.solvd.twitter.service;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class FollowingServiceTest {

    private FollowingService followingService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @BeforeEach
    void setUp() {
        followingService = new UserServiceImpl(userRepository, bCryptPasswordEncoder);
    }

    @Test
    void verifyIsAFollowingTest() {
        String userId = "1";
        String followingId = "1";
        Mockito.when(userRepository.isAFollowing(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(true);
        Boolean result = followingService.isAFollowing(userId, followingId);
        Assertions.assertEquals(true, result,
                "Asser that it is a following");
        Mockito.verify(userRepository, Mockito.times(1))
                .isAFollowing(userId, followingId);
    }

    @Test
    void verifyGetFollowingsNumberTest() {
        String userId = "1";
        Integer followingsAmount = 1;
        Mockito.when(userRepository.getFollowingsNumber(Mockito.anyString()))
                .thenReturn(followingsAmount);
        Integer result = followingService.getFollowingsNumber(userId);
        Assertions.assertEquals(followingsAmount, result,
                "Assert that actual and expected amount are equals");
    }

    @Test
    void verifyCreateFollowingByIdTest() {
        String userId = "1";
        String followingId = "1";
        User user = TwitterFactory.generateUser();
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        followingService.createFollowingById(userId, followingId);
        Mockito.verify(userRepository, Mockito.times(1))
                .findById(userId);
        Mockito.verify(userRepository, Mockito.times(1))
                .createFollowingById(userId, followingId);
    }

    @Test
    void verifyFindFollowingsByUserIdTest() {
        String userId = "1";
        User user = TwitterFactory.generateUser();
        List<User> followings = new ArrayList<>(List.of(user));
        Mockito.when(userRepository.findFollowingsByUserId(Mockito.anyString()))
                .thenReturn(followings);
        List<User> followingsFounded = followingService.findFollowingsByUserId(userId);
        Assertions.assertEquals(followings.size(), followingsFounded.size(),
                "Assert number of followings is the same");
        Mockito.verify(userRepository, Mockito.times(1))
                .findFollowingsByUserId(userId);
    }

    @Test
    void verifyDeleteFollowingByIdTest() {
        String userId = "1";
        String followingId = "1";
        followingService.deleteFollowingById(userId, followingId);
        Mockito.verify(userRepository, Mockito.times(1))
                .deleteFollowingById(userId, followingId);
    }

}
