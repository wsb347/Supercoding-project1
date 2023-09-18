package com.example.project01.controller.Dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDto {
    private String email;
    @NonNull
    private String password;
}
