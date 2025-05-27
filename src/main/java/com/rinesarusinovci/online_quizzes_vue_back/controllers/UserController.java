package com.rinesarusinovci.online_quizzes_vue_back.controllers;

import com.rinesarusinovci.online_quizzes_vue_back.services.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@PreAuthorize("hasRole('USER')")
@RequiredArgsConstructor
public class UserController {
    private final QuizService quizService;


    @GetMapping
    @PreAuthorize("hasAuthority('user:read')")
    public String get() {
        return "GET: user controller";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('user:write')")
    public String post() {
        return "POST: user controller";
    }




}
