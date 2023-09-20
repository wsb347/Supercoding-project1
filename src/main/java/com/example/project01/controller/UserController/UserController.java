package com.example.project01.controller.UserController;

import com.example.project01.controller.Dto.UserDto;
import com.example.project01.controller.Response;
import com.example.project01.service.JwtService;
import com.example.project01.service.UserService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

import javax.servlet.http.HttpServletRequest;
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
        String signup = userService.saveUser(userDto);
        HttpHeaders headers = new HttpHeaders();
        Response signupResponse = new Response();
        signupResponse.setMassage(signup);

        return ResponseEntity.ok()
                .headers(headers)
                .body(signupResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<Response> userLogin(@RequestBody UserDto userDto){
        Response signupResponse = new Response();

        if(jwtService.encode(userDto) != null) {
            HttpHeaders headers = new HttpHeaders();

            String encoded = jwtService.encode(userDto);
            headers.setBearerAuth(encoded);
            signupResponse.setMassage("로그인 성공, JWT 생성이 완료되었습니다");
            return ResponseEntity.status(200).headers(headers).body(signupResponse);

        } else
            signupResponse.setMassage("가입되지 않은 정보입니다. 회원가입을 먼저 해주십시오.");

        return ResponseEntity.status(404).body(signupResponse);




    }

    @PostMapping("/logout")
    public Response userLogout(@RequestHeader("Token") String token){
        String userLogout = userService.userLogout(token.substring(7));

        Response response = new Response();


       response.setMassage(userLogout);

        return response;
    }


}
