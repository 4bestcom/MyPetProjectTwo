package com.hibernatetest.lesson.mapper;

import com.hibernatetest.lesson.entity.User;
import com.hibernatetest.lesson.web.entity.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    void copyUser(@MappingTarget User user, UserDto userDto);
}
