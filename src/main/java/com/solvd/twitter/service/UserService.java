package com.solvd.twitter.service;

import com.solvd.twitter.domain.user.User;

import java.util.List;

public interface UserService {

    User create(User user);

    User findById(String id);

    User update(User user);

    void deleteById(String id);

}
