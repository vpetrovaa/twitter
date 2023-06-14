package com.solvd.twitter.service;

import com.solvd.twitter.domain.user.User;

import java.util.List;

public interface UserService {

    User create(User user);

    User createFollowerById(String id, String followerId);

    User createFollowingById(String id, String followingId);

    User findById(String id);

    List<User> findFollowersByUserId(String id);

    List<User> findFollowingsByUserId(String id);

    User update(User user);

    void deleteById(String id);

}