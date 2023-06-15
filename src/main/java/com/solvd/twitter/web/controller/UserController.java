package com.solvd.twitter.web.controller;

import com.solvd.twitter.domain.user.User;
import com.solvd.twitter.service.FollowerService;
import com.solvd.twitter.service.FollowingService;
import com.solvd.twitter.service.UserService;
import com.solvd.twitter.web.dto.UserDto;
import com.solvd.twitter.web.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final FollowerService followerService;
    private final FollowingService followingService;
    private final UserMapper userMapper;

    @GetMapping("/{id}/followers/is")
    public final Boolean isAFollower(@PathVariable final String id,
                                   @RequestParam final String followerId) {
        return followerService.isAFollower(id, followerId);
    }

    @GetMapping("/{id}/followings/is")
    public final Boolean isAFollowing(@PathVariable final String id,
                              @RequestParam final String followingId) {
        return followingService.isAFollowing(id, followingId);
    }

    @GetMapping("/{id}/followers/count")
    public final Integer countFollowers(@PathVariable final String id) {
        return followerService.getFollowersNumber(id);
    }

    @GetMapping("/{id}/followings/count")
    public final Integer countFollowings(@PathVariable final String id) {
        return followingService.getFollowingsNumber(id);
    }

    @PostMapping
    public final UserDto create(@RequestBody @Validated final UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        user = userService.create(user);
        return userMapper.toDto(user);
    }

    @PostMapping("/{id}/followings")
    public final UserDto createFollowingById(@PathVariable final String id,
                                      @RequestParam final String followingId) {
        User user = followingService.createFollowingById(id, followingId);
        return userMapper.toDto(user);
    }

    @GetMapping("/{id}")
    public final UserDto getById(@PathVariable final String id) {
        User user = userService.findById(id);
        return userMapper.toDto(user);
    }

    @GetMapping("/{id}/followers")
    public final List<UserDto> getFollowersByUserId(@PathVariable final String id) {
        List<User> users = followerService.findFollowersByUserId(id);
        return userMapper.toDto(users);
    }

    @GetMapping("/{id}/followings")
    public final List<UserDto> getFollowingsByUserId(@PathVariable final String id) {
        List<User> users = followingService.findFollowingsByUserId(id);
        return userMapper.toDto(users);
    }

    @PutMapping
    public final UserDto update(@RequestBody @Validated final UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        user = userService.update(user);
        return userMapper.toDto(user);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable final String id) {
        userService.deleteById(id);
    }

    @DeleteMapping("/{id}/followings")
    public void deleteFollowingById(@PathVariable final String id,
                                    @RequestParam final String followingId) {
        followingService.deleteFollowingById(id, followingId);
    }

    @DeleteMapping("/{id}/followers")
    public void deleteFollowerById(@PathVariable final String id,
                                   @RequestParam final String followerId) {
        followerService.deleteFollowerById(id, followerId);
    }

}
