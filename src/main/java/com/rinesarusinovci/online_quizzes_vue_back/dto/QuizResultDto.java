package com.rinesarusinovci.online_quizzes_vue_back.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizResultDto {
    private int totalQuestions;
    private int correctAnswers;
}
