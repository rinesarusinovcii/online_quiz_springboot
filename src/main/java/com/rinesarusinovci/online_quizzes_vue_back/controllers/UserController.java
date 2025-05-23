package com.rinesarusinovci.online_quizzes_vue_back.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@PreAuthorize("hasRole('USER')")

public class UserController {
    @GetMapping
    @PreAuthorize("hasAuthority('admin:read')")
    public String get() {
        return "GET: admin controller";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('admin:write')")
    public String post() {
        return "POST: admin controller";
    }

}
