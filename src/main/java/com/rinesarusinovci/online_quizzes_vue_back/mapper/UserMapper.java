package com.rinesarusinovci.online_quizzes_vue_back.mapper;


import com.rinesarusinovci.online_quizzes_vue_back.dto.RegisterUserDto;
import com.rinesarusinovci.online_quizzes_vue_back.dto.UserDto;
import com.rinesarusinovci.online_quizzes_vue_back.dto.UserListingDto;
import com.rinesarusinovci.online_quizzes_vue_back.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserDto> {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Override
    User toEntity(UserDto userDto);

    @Override
    List<User> toEntities(List<UserDto> userDtos);

    @Override
    UserDto toDto(User user);

    @Override
    List<UserDto> toDtos(List<User> users);

    UserListingDto toUserListingDto(User user);

    User userRequestDtoToUser(RegisterUserDto registerUserRequestDto);


}
