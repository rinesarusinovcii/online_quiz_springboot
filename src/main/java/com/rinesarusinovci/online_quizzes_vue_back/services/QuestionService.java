package com.rinesarusinovci.online_quizzes_vue_back.services;


import com.rinesarusinovci.online_quizzes_vue_back.dto.QuestionDto;
import com.rinesarusinovci.online_quizzes_vue_back.dto.ResultDto;
import com.rinesarusinovci.online_quizzes_vue_back.entities.Question;

import java.util.List;

public interface QuestionService extends BaseService<QuestionDto, Long> {
    List<Question> findByQuizId(Long quizId);


}
