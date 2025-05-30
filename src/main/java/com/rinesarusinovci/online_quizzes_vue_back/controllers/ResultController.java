package com.rinesarusinovci.online_quizzes_vue_back.controllers;


import com.rinesarusinovci.online_quizzes_vue_back.dto.QuizSubmissionDto;
import com.rinesarusinovci.online_quizzes_vue_back.dto.ResultDto;
import com.rinesarusinovci.online_quizzes_vue_back.entities.Question;
import com.rinesarusinovci.online_quizzes_vue_back.services.QuestionService;
import com.rinesarusinovci.online_quizzes_vue_back.services.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/results")
@RequiredArgsConstructor
public class ResultController {

    private final ResultService resultService;
    private final QuestionService questionService;

    @GetMapping
    public ResponseEntity<List<ResultDto>> getAllResults() {

        return ResponseEntity.ok(resultService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultDto> getResultById(@PathVariable Long id) {
        return ResponseEntity.ok(resultService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ResultDto> saveResult(@RequestBody ResultDto resultDto) {
        return ResponseEntity.ok(resultService.add(resultDto));
    }

    // Metoda e re për të kalkuluar dhe ruajtur rezultatin bazuar në përgjigjet e përdoruesit dhe quizId
    @PostMapping("/calculate/{quizId}")
    public ResponseEntity<ResultDto> calculateAndSaveResult(
            @PathVariable Long quizId,
            @RequestBody Map<Long, Long> userAnswers,Long userId) {

        // Marrim pyetjet sipas quizId nga QuestionService
        List<Question> questions = questionService.findByQuizId(quizId);

        // Llogarisim dhe ruajmë rezultatin
        ResultDto resultDto = resultService.saveCalculatedResult(questions, userAnswers, quizId,userId);

        return ResponseEntity.ok(resultDto);
    }


}
