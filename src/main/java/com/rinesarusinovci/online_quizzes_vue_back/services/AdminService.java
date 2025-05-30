package com.rinesarusinovci.online_quizzes_vue_back.services;

import com.rinesarusinovci.online_quizzes_vue_back.dto.UserDto;

import java.util.List;

public interface AdminService {
    List<UserDto> getAllUsers();
    void deleteUser(Long id);
    void updateUser(Long id, UserDto userDto);

}
