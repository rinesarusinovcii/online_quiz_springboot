package com.rinesarusinovci.online_quizzes_vue_back.controllers;

import com.rinesarusinovci.online_quizzes_vue_back.dto.ChoiceDto;
import com.rinesarusinovci.online_quizzes_vue_back.services.ChoiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/choices")
@RequiredArgsConstructor
public class ChoiceController {
    private final ChoiceService choiceService;

    @GetMapping
    public ResponseEntity<List<ChoiceDto>> findAll() {
        return ResponseEntity.ok(choiceService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChoiceDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(choiceService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ChoiceDto> create(@Valid @RequestBody ChoiceDto request) {
        return ResponseEntity.ok(choiceService.add(request));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody ChoiceDto request) {
        choiceService.modify(id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        choiceService.removeById(id);
        return ResponseEntity.noContent().build();
    }
}
