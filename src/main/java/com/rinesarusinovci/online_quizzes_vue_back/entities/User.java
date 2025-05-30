package com.rinesarusinovci.online_quizzes_vue_back.entities;



import com.rinesarusinovci.online_quizzes_vue_back.enums.Role;
import com.rinesarusinovci.online_quizzes_vue_back.infrastructure.AgeBetween;
import com.rinesarusinovci.online_quizzes_vue_back.infrastructure.Contains;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PositiveOrZero(message = "Id must be a positive number")
    private long id;

    @Column(nullable = false, length = 25)
    @NotNull(message = "Name is required")
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 25, message = "Name must be between 2 and 25 characters")
    private String name;

    @Column(nullable = false, length = 25)
    @NotNull(message = "Surname is required")
    @NotBlank(message = "Surname is required")
    @Size(min = 4, max = 25, message = "Surname must be between 2 and 25 characters")
    private String surname;


    @Column(nullable = false)
    @AgeBetween(min = 16, max = 110, message = "You should be between 16 and 110 years old")
    private LocalDate birthdate;

    @Column(nullable = false, unique = true, length = 50)
    @NotNull(message = "Username is required")
    @NotBlank(message = "Username is required")
    @Size(min = 5, max = 50, message = "Username must be between 5 and 50 characters")
    private String username;

    @Column(nullable = false, length = 50)
    @NotNull(message = "Email is required")
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Contains(value = "@", message = "Email should contain @ symbol")
    private String email;

    @Column(nullable = false, length = 255)
    private String password;



    @Column(nullable = false)
    @NotNull(message = "Role is required")
    @Enumerated(EnumType.STRING)
    private Role role;


    @OneToMany(mappedBy = "createdBy")
    private List<Quiz> quizzes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Result> results;



}
