package com.example.project01.service.UserService;

import com.example.project01.Entity.UserEntity;
import com.example.project01.controller.Dto.UserDto;
import com.example.project01.repository.UserRepository.UserRepository;
import com.example.project01.service.CryptoService;
import com.example.project01.service.JwtService;
import com.example.project01.service.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final CryptoService cryptoService;

    public String saveUser(UserDto userDto) {
        Optional<UserEntity> byEmail = userRepository.findByEmail(userDto.getEmail());

        if (byEmail.isPresent()) {

                return "이미 가입된 정보입니다, 같은 email이 등록되어 있습니다.";
            } else {

                passwordEncoding(userDto);

                UserEntity userEntity = UserMapper.INSTANCE.UserDtoToUserEntity(userDto);
                userRepository.save(userEntity);
                return "회원가입이 완료되었습니다";

            }
        }







    public String userLogout(String token) {
        return getString(token);
    }


    public String userLogin(UserDto userDto) {
        Optional<UserEntity> byEmail = userRepository.findByEmail(userDto.getEmail());

        if(byEmail.isPresent()) {
            String password = byEmail.get().getPassword();
            boolean matches = cryptoService.passwordEncoder().matches(userDto.getPassword(), password);
            if (matches){
                return jwtService.encode(userDto);
            }  else return null;
        } else return null;
    }



    private String getString(String token) {
        if (jwtService.isTokenExpired(token)) {
            return "만료된 토큰입니다";
        }
        if (!jwtService.isPresent(token)) {
            return "가입되지 않은 정보입니다. 토큰을 다시 한 번 확인해주세요";
        } else return "로그아웃 하였습니다.";
    }

    private UserDto passwordEncoding(UserDto userDto) {
        String userEncodedPWD = cryptoService.passwordEncoder().encode(userDto.getPassword());
        userDto.setPassword(userEncodedPWD);
        return userDto;
    }
}
