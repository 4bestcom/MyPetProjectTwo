package com.hibernatetest.lesson.web.mapper;

import com.hibernatetest.lesson.entity.User;
import com.hibernatetest.lesson.web.entity.UserDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {

    User toUser(UserDto userDto);

    UserDto fromUser(User user);

    List<UserDto> mapListUserToListUserDto(List<User> users);
}
