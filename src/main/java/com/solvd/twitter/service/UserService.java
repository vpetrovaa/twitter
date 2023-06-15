package com.solvd.twitter.service;

import com.solvd.twitter.domain.user.User;

import java.util.List;

public interface UserService {

    Boolean isAFollower(String id, String followerId);

    Boolean isAFollowing(String id, String followingId);

    Integer getFollowersNumber(String id);

    Integer getFollowingsNumber(String id);

    User create(User user);

    User createFollowingById(String id, String followingId);

    User findById(String id);

    List<User> findFollowersByUserId(String id);

    List<User> findFollowingsByUserId(String id);

    User update(User user);

    void deleteById(String id);

    void deleteFollowingById(String id, String followingId);

    void deleteFollowerById(String id, String followerId);

}
