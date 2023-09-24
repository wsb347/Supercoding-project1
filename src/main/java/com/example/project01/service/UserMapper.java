package com.example.project01.service;

import com.example.project01.entity.UserEntity;
import com.example.project01.controller.Dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserEntity UserDtoToUserEntity(UserDto userDto);

    UserDto UserEntityToUserDto(UserEntity userEntity);

}