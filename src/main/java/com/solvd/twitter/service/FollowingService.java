package com.solvd.twitter.service;

import com.solvd.twitter.domain.user.User;

import java.util.List;

public interface FollowingService {

    Boolean isAFollowing(String id, String followingId);

    Integer getFollowingsNumber(String id);

    User createFollowingById(String id, String followingId);

    List<User> findFollowingsByUserId(String id);

    void deleteFollowingById(String id, String followingId);

}
