package com.rinesarusinovci.online_quizzes_vue_back.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultDto {
    private long id;
    private long quizId;
    @NotNull
    @PositiveOrZero(message = "Score must be a positive number")
    private double score;

    @NotNull
    @PositiveOrZero(message = "Correct answers must be a positive number")
    private int correctAnswers;

    @NotNull
    @PositiveOrZero(message = "Wrong answers must be a positive number")
    private int wrongAnswers;

    @NotNull
    private Boolean passed;

     private Long userId;
}
