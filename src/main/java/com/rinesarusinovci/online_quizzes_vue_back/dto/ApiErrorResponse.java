package com.rinesarusinovci.online_quizzes_vue_back.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiErrorResponse {

    private int status;
    private String message;
    private Map<String, String> validationErrors = new HashMap<>();
}
