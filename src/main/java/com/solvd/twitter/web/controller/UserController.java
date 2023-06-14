package com.solvd.twitter.web.controller;

import com.solvd.twitter.domain.user.User;
import com.solvd.twitter.service.UserService;
import com.solvd.twitter.web.dto.UserDto;
import com.solvd.twitter.web.dto.validation.OnCreate;
import com.solvd.twitter.web.dto.validation.OnUpdate;
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
    private final UserMapper userMapper;

    @PostMapping
    @Validated(OnCreate.class)
    public final UserDto create(@RequestBody final UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        user = userService.create(user);
        return userMapper.toDto(user);
    }

    @PostMapping("/{id}/following")
    @Validated(OnCreate.class)
    public final UserDto createFollowingById(@PathVariable final String id,
                                      @RequestBody final String followingId) {
        User user = userService.createFollowingById(id, followingId);
        return userMapper.toDto(user);
    }

    @GetMapping("/{id}")
    public final UserDto getById(@PathVariable final String id) {
        User user = userService.findById(id);
        return userMapper.toDto(user);
    }

    @GetMapping("/{id}/followers")
    public final List<UserDto> getFollowersByUserId(@PathVariable final String id) {
        List<User> users = userService.findFollowersByUserId(id);
        return userMapper.toDto(users);
    }

    @GetMapping("/{id}/followings")
    public final List<UserDto> getFollowingsByUserId(@PathVariable final String id) {
        List<User> users = userService.findFollowingsByUserId(id);
        return userMapper.toDto(users);
    }

    @PutMapping
    @Validated(OnUpdate.class)
    public final UserDto update(@RequestBody final UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        user = userService.update(user);
        return userMapper.toDto(user);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable final String id) {
        userService.deleteById(id);
    }

}
