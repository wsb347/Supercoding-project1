package com.example.project01.service.UserService;

import com.example.project01.Entity.UserEntity;
import com.example.project01.controller.Dto.UserDto;
import com.example.project01.repository.UserRepository.UserRepository;
import com.example.project01.service.JwtService;
import com.example.project01.service.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.security.SignatureException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;


    public String saveUser(UserDto userDto) {
        Optional<UserEntity> byEmailAndPassword = userRepository.findByEmailAndPassword(userDto.getEmail(), userDto.getPassword());
        if (byEmailAndPassword.isPresent()) {
            return "이미 가입된 정보입니다";
        } else {


            UserEntity userEntity = UserMapper.INSTANCE.UserDtoToUserEntity(userDto);
            userRepository.save(userEntity);
            return "회원가입이 완료되었습니다";

        }
    }



    public String userLogout(String token) {
        return getString(token);


    }

    private String getString(String token) {
        if(jwtService.isTokenExpired(token)){
            return "만료된 토큰입니다";
        }
        if(!jwtService.isPresent(token)){
            return "가입되지 않은 정보입니다. 토큰을 다시 한 번 확인해주세요";
        } else return "토큰을 강제 만료 시킵니다. 로그아웃 하였습니다.";
    }
}



