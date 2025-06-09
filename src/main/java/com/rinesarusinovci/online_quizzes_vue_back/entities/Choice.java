package com.rinesarusinovci.online_quizzes_vue_back.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "choices")
public class Choice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PositiveOrZero(message = "Id must be a positive number")
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "Text is required")
   @NotBlank(message = "Text is required")
   @Size(min = 2, max = 100, message = "Text must be between 10 and 2000 characters")
    private String text;

    @Column(nullable = false)
    private boolean correct;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

}
