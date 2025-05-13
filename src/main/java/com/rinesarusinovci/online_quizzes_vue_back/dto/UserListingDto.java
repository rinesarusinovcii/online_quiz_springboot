package com.rinesarusinovci.online_quizzes_vue_back.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserListingDto {
    private Long id;
    private String email;
    private String username;
}
