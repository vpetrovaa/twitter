package com.solvd.twitter.service;

import com.solvd.twitter.domain.user.User;

import java.util.List;

public interface FollowerService {

    Boolean isAFollower(String id, String followerId);

    Integer getFollowersNumber(String id);

    List<User> findFollowersByUserId(String id);

    void deleteFollowerById(String id, String followerId);

}
