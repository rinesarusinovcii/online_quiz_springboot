package com.rinesarusinovci.online_quizzes_vue_back.dto;

import com.rinesarusinovci.online_quizzes_vue_back.entities.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizSubmissionDto {
    private Map<String, String> answers;



}