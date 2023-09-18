package com.example.project01.config;

import com.example.project01.service.JwtService;
import com.example.project01.controller.Dto.UserDto;

import com.example.project01.controller.Dto.UserDto;
import com.example.project01.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class UserAuthArgumentResolver implements HandlerMethodArgumentResolver {

    private final JwtService jwtService;


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return UserDto.class.isAssignableFrom(parameter.getParameterType());
    }


    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory)  {
        String authorization = webRequest.getHeader("Authorization");
        if(authorization == null || !authorization.startsWith("Bearer ")){
            throw new RuntimeException("UnauthorizedException");
        }
        String token = authorization.substring(7);
        Map<String, String> decodedToken = jwtService.decode(token);
        String memberPassword = decodedToken.get(JwtService.CLAIM_NAME_MEMBER_PASSWORD);
        if(memberPassword == null){
            throw new RuntimeException("UnauthorizedException");
        }
        return new UserDto(memberPassword);

    }
}
