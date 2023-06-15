package com.solvd.twitter.service.impl;

import com.solvd.twitter.domain.exception.ResourceAlreadyExistsException;
import com.solvd.twitter.domain.exception.ResourceDoesNotExistException;
import com.solvd.twitter.domain.user.User;
import com.solvd.twitter.repository.UserRepository;
import com.solvd.twitter.service.FollowerService;
import com.solvd.twitter.service.FollowingService;
import com.solvd.twitter.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, FollowerService, FollowingService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Boolean isAFollower(final String id, final String followerId) {
        return userRepository.isAFollower(id, followerId);
    }

    @Override
    public Boolean isAFollowing(final String id, final String followingId) {
        return userRepository.isAFollowing(id, followingId);
    }

    @Override
    public Integer getFollowersNumber(final String id) {
        return userRepository.getFollowersNumber(id);
    }

    @Override
    public Integer getFollowingsNumber(final String id) {
        return userRepository.getFollowingsNumber(id);
    }

    @Override
    public User create(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ResourceAlreadyExistsException("User with email " +
                    user.getEmail() + " already exists");
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User createFollowingById(final String id, final String followingId) {
        userRepository.createFollowingById(id, followingId);
        return findById(followingId);
    }

    @Override
    public User findById(final String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceDoesNotExistException("There" +
                        " are no user with id " + id));
    }

    @Override
    public List<User> findFollowersByUserId(final String id) {
        return userRepository.findFollowersByUserId(id);
    }

    @Override
    public List<User> findFollowingsByUserId(final String id) {
        return userRepository.findFollowingsByUserId(id);
    }

    @Override
    public User update(User user) {
        return userRepository.update(user);
    }

    @Override
    public void deleteById(final String id) {
        userRepository.deleteById(id);
    }

    @Override
    public void deleteFollowingById(final String id, final String followingId) {
        userRepository.deleteFollowingById(id, followingId);
    }

    @Override
    public void deleteFollowerById(final String id, final String followerId) {
        userRepository.deleteFollowerById(id, followerId);
    }

}
