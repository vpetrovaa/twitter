package com.solvd.twitter.service.impl;

import com.solvd.twitter.domain.exception.ResourceAlreadyExistsException;
import com.solvd.twitter.domain.exception.ResourceDoesNotExistException;
import com.solvd.twitter.domain.user.User;
import com.solvd.twitter.repository.UserRepository;
import com.solvd.twitter.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

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
    public User createFollowerById(final String id, final String followerId) {
        User follower = findById(followerId);
        userRepository.createFollowerById(id, followerId);
        return follower;
    }

    @Override
    public User createFollowingById(final String id, final String followingId) {
        User following = findById(followingId);
        userRepository.createFollowingById(id, followingId);
        return following;
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
    public User update(final User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(final String id) {
        userRepository.deleteById(id);
    }

}
