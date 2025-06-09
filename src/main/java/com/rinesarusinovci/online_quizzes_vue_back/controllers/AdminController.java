package com.rinesarusinovci.online_quizzes_vue_back.controllers;

import com.rinesarusinovci.online_quizzes_vue_back.dto.QuizDto;
import com.rinesarusinovci.online_quizzes_vue_back.dto.UserDto;
import com.rinesarusinovci.online_quizzes_vue_back.services.AdminService;
import com.rinesarusinovci.online_quizzes_vue_back.services.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class  AdminController {
    private final QuizService quizService;
    private final AdminService adminService;
    @GetMapping
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<List<QuizDto>> getAllQuizzes() {
        return ResponseEntity.ok(quizService.findAll());
    }



    @GetMapping("/users")
    @PreAuthorize("hasAuthority('admin:read')")
    public List<UserDto> getAllUsers() {
        return adminService.getAllUsers();
    }

    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasAuthority('admin:write')")
    public void deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        adminService.updateUser(id, userDto);
        return ResponseEntity.ok().build();
    }
}
