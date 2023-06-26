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

@ExtendWith(MockitoExtension.class)
public class FollowerServiceTest {

    private FollowerService followerService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @BeforeEach
    void setUp(){
        followerService = new UserServiceImpl(userRepository, bCryptPasswordEncoder);
    }

    @Test
    void verifyIsAFollowerTest() {
        String userId = "1";
        String followerId = "1";
        Mockito.when(userRepository.isAFollower(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(true);
        Boolean result = followerService.isAFollower(userId, followerId);
        Assertions.assertEquals(true, result,
                "Asser that it is a follower");
        Mockito.verify(userRepository, Mockito.times(1))
                .isAFollower(userId, followerId);
    }

    @Test
    void verifyGetFollowersNumberTest() {
        String userId = "1";
        Integer followersAmount = 1;
        Mockito.when(userRepository.getFollowersNumber(Mockito.anyString()))
                .thenReturn(followersAmount);
        Integer result = followerService.getFollowersNumber(userId);
        Assertions.assertEquals(followersAmount, result,
                "Assert that actual and expected amount are equals");
    }

    @Test
    void verifyFindFollowersByUserIdTest() {
        String userId = "1";
        User user = TwitterFactory.generateUser();
        List<User> followers = new ArrayList<>(List.of(user));
        Mockito.when(userRepository.findFollowersByUserId(Mockito.anyString()))
                .thenReturn(followers);
        List<User> followersFounded = followerService.findFollowersByUserId(userId);
        Assertions.assertEquals(followers.size(), followersFounded.size(),
                "Assert number of followers is the same");
        Mockito.verify(userRepository, Mockito.times(1))
                .findFollowersByUserId(userId);
    }

    @Test
    void verifyDeleteFollowerByIdTest() {
        String userId = "1";
        String followerId = "1";
        followerService.deleteFollowerById(userId, followerId);
        Mockito.verify(userRepository, Mockito.times(1))
                .deleteFollowerById(userId, followerId);
    }

}
