package com.rinesarusinovci.online_quizzes_vue_back.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChoiceDto {
    private Long id;
    private String text;
    private Long questionId;
    private boolean correct;
}
