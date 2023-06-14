package com.solvd.twitter.web.mapper;

import com.solvd.twitter.domain.user.User;
import com.solvd.twitter.web.dto.UserDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    User toEntity(UserDto userDto);

    List<UserDto> toDto(List<User> users);

}
