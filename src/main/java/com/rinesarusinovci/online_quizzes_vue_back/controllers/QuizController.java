package com.rinesarusinovci.online_quizzes_vue_back.controllers;


import com.rinesarusinovci.online_quizzes_vue_back.dto.QuizDto;
import com.rinesarusinovci.online_quizzes_vue_back.security.AppUserDetails;
import com.rinesarusinovci.online_quizzes_vue_back.services.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/quizzes")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class QuizController {
    private final QuizService quizService;



    @GetMapping
    public ResponseEntity<List<QuizDto>> findAll(){

        return ResponseEntity.ok(quizService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizDto> findById(@PathVariable long id){
        return ResponseEntity.ok(quizService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<QuizDto> create(@RequestBody QuizDto request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();

        long loggedUserId = userDetails.getId();
        request.setCreatedBy(loggedUserId);
        return new ResponseEntity<>(quizService.add(request), HttpStatus.CREATED);

    }


    @PutMapping("/{id}")
    public ResponseEntity<QuizDto> update(@PathVariable long id, @RequestBody QuizDto request) {
        QuizDto update =quizService.modify(id, request);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable long id) {
        quizService.removeById(id);
        return ResponseEntity.noContent().build();
    }
}
