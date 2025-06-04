package com.rinesarusinovci.online_quizzes_vue_back.controllers;

import com.rinesarusinovci.online_quizzes_vue_back.dto.QuizDto;
import com.rinesarusinovci.online_quizzes_vue_back.services.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@PreAuthorize("hasRole('USER')")
@RequiredArgsConstructor
public class UserController {
    private final QuizService quizService;


//    @GetMapping
//    @PreAuthorize("hasAuthority('user:read')")
//    public ResponseEntity<List<QuizDto>> getAllQuizzes() {
//        return ResponseEntity.ok(quizService.findAll());
//    }

    @PostMapping
    @PreAuthorize("hasAuthority('user:write')")
    public String post() {
        return "POST: user controller";
    }




}
