package com.rinesarusinovci.online_quizzes_vue_back.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDto {
    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @NotBlank
    private String username;

    private LocalDate birthdate;
    private String email;
}
