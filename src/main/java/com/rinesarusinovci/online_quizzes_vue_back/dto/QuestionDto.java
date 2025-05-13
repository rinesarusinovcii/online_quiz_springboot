package com.rinesarusinovci.online_quizzes_vue_back.dto;


import com.rinesarusinovci.online_quizzes_vue_back.enums.QuestionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {
    private long id;

    @NotBlank(message = "Question is required")
    @Size(min = 10, max = 2000, message = "Question must be between 10 and 2000 characters")
    private String text;

    @NotNull(message = "Question type is required")
    private QuestionType questionType;

    @NotNull(message = "Points is required")
    @PositiveOrZero(message = "Points must be a positive number")
    private double points;

    private Long quizId;

    @NotNull(message = "Choices are required")
    @Size(min = 1, message = "At least one choice is required")
    private List<@NotBlank(message = "Choice cannot be blank") String> choices;

    private boolean correct;
}
