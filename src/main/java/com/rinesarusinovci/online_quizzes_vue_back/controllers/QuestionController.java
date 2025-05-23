package com.rinesarusinovci.online_quizzes_vue_back.controllers;


import com.rinesarusinovci.online_quizzes_vue_back.dto.QuestionDto;
import com.rinesarusinovci.online_quizzes_vue_back.services.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;





    @GetMapping
    public ResponseEntity<List<QuestionDto>> findAll(){

        return ResponseEntity.ok(questionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionDto> findById(@PathVariable long id){
        return ResponseEntity.ok(questionService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<QuestionDto> create(@Valid@RequestBody QuestionDto request){
        return new ResponseEntity<>(questionService.add(request), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<QuestionDto> update(@PathVariable long id, @Valid @RequestBody QuestionDto request) {
        QuestionDto updatedQuestion = questionService.modify(id, request);
        return ResponseEntity.ok(updatedQuestion);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable long id) {
        questionService.removeById(id);
        return ResponseEntity.noContent().build();
    }

}
