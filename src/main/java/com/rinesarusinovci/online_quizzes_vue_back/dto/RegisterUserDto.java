package com.rinesarusinovci.online_quizzes_vue_back.dto;



import com.rinesarusinovci.online_quizzes_vue_back.enums.Role;
import com.rinesarusinovci.online_quizzes_vue_back.infrastructure.AgeBetween;
import com.rinesarusinovci.online_quizzes_vue_back.infrastructure.Contains;
import com.rinesarusinovci.online_quizzes_vue_back.infrastructure.SameAs;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@SameAs(field = "password", sameAsField = "confirmPassword", message = "Passwords do not match")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserDto {

    @Size(min = 4, max = 50, message = "Username should be between 3 and 50 characters")
    @NotBlank(message = "Username should not be empty or blank")
    @NotNull(message = "Username is required")
    private String username;

    @Size(min = 3, max = 50, message = "Email should be between 3 and 50 characters")
    @NotBlank(message = "Email should not be empty or blank")
    @Email(message = "Email should be valid")
    @Contains(value = "@", message = "Email should contain @ symbol")
    private String email;

    @Size(min = 2, max = 25, message = "Name should be between 3 and 50 characters")
    @NotBlank(message = "Name should not be empty or blank")
    @NotNull(message = "Name is required")
    private String name;

    @Size(min = 4, max = 25, message = "Surname should be between 3 and 50 characters")
    @NotBlank(message = "Surname should not be empty or blank")
    @NotNull(message = "Surname is required")
    private String surname;


    @AgeBetween(min = 16, max = 110, message = "You should be between 16 and 110 years old")
    private LocalDate birthdate;

    @NotNull(message = "Date of birth is required")
    private Role role;



    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "Password should contain at least one uppercase letter, one lowercase letter and one digit")
    private String password;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "Password should contain at least one uppercase letter, one lowercase letter and one digit")

    private String confirmPassword;


//    @Size(min = 8, max = 50, message = "Password should be between 6 and 100 characters")
//    @NotBlank(message = "Password should not be empty or blank")
//    @NotNull(message = "Password is required")
//    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "Password should contain at least one uppercase letter, one lowercase letter and one digit")
//    private String confirmPassword;

//    @AssertTrue(message = "You must accept the terms and conditions")
//    private boolean acceptTerms;
}
