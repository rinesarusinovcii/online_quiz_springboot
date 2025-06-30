package com.rinesarusinovci.online_quizzes_vue_back.services;


import com.rinesarusinovci.online_quizzes_vue_back.dto.ChangePasswordDto;
import com.rinesarusinovci.online_quizzes_vue_back.dto.RegisterUserDto;
import com.rinesarusinovci.online_quizzes_vue_back.dto.UpdateUserDto;
import com.rinesarusinovci.online_quizzes_vue_back.entities.User;
import com.rinesarusinovci.online_quizzes_vue_back.security.AppUserDetails;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationService {
    UserDetails login(String email, String password);

    String generateToken(UserDetails userDetails);

    UserDetails register(RegisterUserDto registerDto);

    String refreshToken(User user);

    User updateProfile(UpdateUserDto dto, AppUserDetails userDetails);

    void deleteUser(Long userId);
    UserDetails validateToken(String token);
    User changePassword(ChangePasswordDto dto, AppUserDetails userDetails);



}
