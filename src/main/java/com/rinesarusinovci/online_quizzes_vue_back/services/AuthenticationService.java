package com.rinesarusinovci.online_quizzes_vue_back.services;


import com.rinesarusinovci.online_quizzes_vue_back.dto.RegisterUserDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationService {
    UserDetails login(String email, String password);

    String generateToken(UserDetails userDetails);

    UserDetails register(RegisterUserDto registerDto);



    UserDetails validateToken(String token);
//    UserDetails updateProfile(String email, RegisterUserDto updatedUserDto);
//    void deleteUser(String email);

}
