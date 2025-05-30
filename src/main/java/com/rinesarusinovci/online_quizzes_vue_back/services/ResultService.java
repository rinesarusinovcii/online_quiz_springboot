package com.rinesarusinovci.online_quizzes_vue_back.services;


import com.rinesarusinovci.online_quizzes_vue_back.dto.ResultDto;
import com.rinesarusinovci.online_quizzes_vue_back.entities.Question;

import java.util.List;
import java.util.Map;

public interface ResultService extends BaseService<ResultDto,Long>{
    ResultDto saveCalculatedResult(List<Question> questions, Map<Long, Long> userAnswers, Long quizId, Long userId);
}

