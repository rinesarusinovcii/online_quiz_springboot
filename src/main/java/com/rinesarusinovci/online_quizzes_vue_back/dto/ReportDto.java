package com.rinesarusinovci.online_quizzes_vue_back.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportDto {
    private long id;
    private long quizId;
    @NotNull(message = "Average score is required")
    @Positive
    private double averageScore;
    @NotNull(message = "Highest score is required")
    @Positive
    private double highestScore;

    private double lowestScore;


    private LocalDate createdAt;
}
