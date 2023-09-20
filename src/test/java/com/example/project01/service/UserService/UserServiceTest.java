package com.example.project01.service.UserService;

import com.example.project01.Entity.UserEntity;
import com.example.project01.controller.Dto.UserDto;
import com.example.project01.repository.UserRepository.UserRepository;
import com.example.project01.service.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;



    @Test
    void saveUser() {
        // given
        UserDto user = new UserDto("jky00914@naver.com", "rmsdud12@");

        userService.saveUser(user);
        Optional<UserEntity> savedUser = userRepository.findByEmail(user.getEmail());

        if(savedUser.isPresent()) {
            long id = savedUser.get().getId();
            assertThat(id).isEqualTo(2);
        }



    }

    @Test
    void userLogout() {
    }
}