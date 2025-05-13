package com.rinesarusinovci.online_quizzes_vue_back.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

    private String email;


    private String password;


    private boolean rememberMe;
}
