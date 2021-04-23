package com.hibernatetest.lesson.mapper;

import com.hibernatetest.lesson.enity.User;
import com.hibernatetest.lesson.web.entity.UserDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User mapUserDtoToUser(UserDto userDto);

    UserDto mapUserToUserDto(User user);

    List<UserDto> mapListUserToListUserDto(List<User> users);

    //используется ко всем найденным типам при маппинге, т.е. к каждому стрингу
//    default String mapName(String name) {
//        return name + " testMapping";
//    }
}
