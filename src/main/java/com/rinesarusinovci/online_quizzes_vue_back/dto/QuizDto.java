package com.rinesarusinovci.online_quizzes_vue_back.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizDto {
    private long id;

    @NotNull(message = "Title is required")
    @NotBlank(message = "Title is required")
    @Size(min = 10, max = 50, message = "Title must be between 10 and 50 characters")
    private String title;

    @Size(min = 10, max = 1000, message = "Description must be between 10 and 1000 characters")
    private String description;

    @NotNull(message = "Category is required")
    @NotBlank(message = "Category is required")
    @Size(min = 5, max = 20, message = "Category must be between 5 and 20 characters")
    private String category;


    private boolean isPublished;

    @NotNull(message = "Created at is required")
    @PastOrPresent(message = "Created at must be in the past or present")
    private LocalDate createdAt;

    @NotNull(message = "Time limit is required")
    @Positive(message = "Time limit must be a positive number")
    private int timeLimit;

//    private String createdBy;
    private Long createdBy;
    private List<QuestionDto> questions;
//    private long report;
//    private List<Long> results;
}
