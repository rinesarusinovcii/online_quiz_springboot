package com.rinesarusinovci.online_quizzes_vue_back.services;


import com.rinesarusinovci.online_quizzes_vue_back.dto.QuizDto;
import com.rinesarusinovci.online_quizzes_vue_back.dto.QuizResultDto;
import com.rinesarusinovci.online_quizzes_vue_back.dto.QuizSubmissionDto;
import com.rinesarusinovci.online_quizzes_vue_back.entities.Quiz;

import java.util.List;

public interface QuizService extends BaseService<QuizDto, Long> {
    QuizResultDto evaluateSubmission(Long quizId, QuizSubmissionDto submission);

     List<QuizDto> getQuizzesByUserId(Long userId);

}
