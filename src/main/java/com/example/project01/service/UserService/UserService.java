package com.example.project01.service.UserService;

import com.example.project01.Entity.UserEntity;
import com.example.project01.controller.Dto.UserDto;
import com.example.project01.repository.UserRepository.UserRepository;
import com.example.project01.service.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;



    public void saveUser(UserDto userDto) {
        UserEntity userEntity = UserMapper.INSTANCE.UserDtoToUserEntity(userDto);
        userRepository.save(userEntity);
    }
}
