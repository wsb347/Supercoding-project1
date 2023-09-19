package com.example.project01.controller.UserController;

import com.example.project01.controller.Dto.UserDto;
import com.example.project01.controller.Response;
import com.example.project01.service.JwtService;
import com.example.project01.service.UserService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ResourceBundle;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;


    @PostMapping("/signup")
    public ResponseEntity<Response> signup(@RequestBody UserDto userDto)
    {
        userService.saveUser(userDto);
        String password = jwtService.encode(userDto.getPassword());

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(password);

        Response signupResponse = new Response();
        signupResponse.setMassage("회원가입이 완료되었습니다.");

        return ResponseEntity.ok()
                .headers(headers)
                .body(signupResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<Response> userLogin(@RequestBody UserDto userDto){
        Response signupResponse = new Response();
        signupResponse.setMassage("로그인이 성공적으로 완료되었습니다.");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String encoded = jwtService.encode(userDto.getPassword());
        headers.setBearerAuth(encoded);

        return ResponseEntity.ok().headers(headers).body(signupResponse);


    }
}
