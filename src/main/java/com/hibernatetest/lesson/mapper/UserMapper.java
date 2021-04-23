package com.hibernatetest.lesson.mapper;

import com.hibernatetest.lesson.enity.User;
import com.hibernatetest.lesson.web.entity.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User mapUserDtoToUser(UserDto userDto);

    UserDto mapUserToUserDto(User user);

    List<UserDto> mapListUserToListUserDto(List<User> users);

    @Mapping(target = "id", ignore = true)
    void copyUser(@MappingTarget User user, UserDto userDto);
}
