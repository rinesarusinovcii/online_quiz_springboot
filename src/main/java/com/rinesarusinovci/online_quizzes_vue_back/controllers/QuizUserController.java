package com.rinesarusinovci.online_quizzes_vue_back.controllers;

import com.rinesarusinovci.online_quizzes_vue_back.dto.QuizDto;
import com.rinesarusinovci.online_quizzes_vue_back.dto.QuizResultDto;
import com.rinesarusinovci.online_quizzes_vue_back.dto.QuizSubmissionDto;
import com.rinesarusinovci.online_quizzes_vue_back.services.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user/quizzes")
@PreAuthorize("hasRole('USER')")
@RequiredArgsConstructor
public class QuizUserController {

    private final QuizService quizService;



    @GetMapping("/all")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<List<QuizDto>> getAllQuizzes() {

        return ResponseEntity.ok(quizService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<QuizDto> getQuiz(@PathVariable Long id) {
        return ResponseEntity.ok(quizService.findById(id));
    }

    @PostMapping("/{id}/submit")
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<QuizResultDto> submitQuiz(
            @PathVariable Long id,
            @RequestBody QuizSubmissionDto submission
    ) {
        QuizResultDto result = quizService.evaluateSubmission(id, submission);
        return ResponseEntity.ok(result);
    }

//    @GetMapping("/user/{userId}")
//    public ResponseEntity<List<QuizDto>> getQuizzesByUserId(@PathVariable Long userId) {
//        List<QuizDto> userQuizzes = quizService.getQuizzesByUserId(userId);
//        return ResponseEntity.ok(userQuizzes);
//    }
}
