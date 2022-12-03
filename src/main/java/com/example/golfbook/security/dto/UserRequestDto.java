package com.example.golfbook.security.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserRequestDto {


    private String userId;
    private String displayName;
    private String email;
    private String password;
    private int handicap;
}
