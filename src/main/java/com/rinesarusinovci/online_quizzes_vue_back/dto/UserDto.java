package com.rinesarusinovci.online_quizzes_vue_back.dto;
import com.rinesarusinovci.online_quizzes_vue_back.enums.Role;
import com.rinesarusinovci.online_quizzes_vue_back.infrastructure.AgeBetween;
import com.rinesarusinovci.online_quizzes_vue_back.infrastructure.Contains;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserDto {
    private Long id;

    @NotNull(message = "Name is required")
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 25, message = "Name must be between 2 and 25 characters")
    private String name;

    @NotNull(message = "Surname is required")
    @NotBlank(message = "Surname is required")
    @Size(min = 4, max = 25, message = "Surname must be between 2 and 25 characters")
    private String surname;

    @AgeBetween(min = 16, max = 110, message = "You should be between 16 and 110 years old")
    private LocalDate birthdate;

    @NotNull(message = "Username is required")
    @NotBlank(message = "Username is required")
    @Size(min = 5, max = 50, message = "Username must be between 5 and 50 characters")
    private String username;

    @NotNull(message = "Email is required")
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Contains(value = "@", message = "Email should contain @ symbol")
    private String email;

    @NotNull(message = "Role is required")
    @Enumerated(EnumType.STRING)
    private Role role;

    private String imagePath;


}
