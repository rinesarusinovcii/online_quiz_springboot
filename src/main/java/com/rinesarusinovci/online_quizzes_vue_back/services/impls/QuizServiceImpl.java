package com.rinesarusinovci.online_quizzes_vue_back.services.impls;


import com.rinesarusinovci.online_quizzes_vue_back.dto.QuizDto;
import com.rinesarusinovci.online_quizzes_vue_back.entities.Question;
import com.rinesarusinovci.online_quizzes_vue_back.entities.Quiz;
import com.rinesarusinovci.online_quizzes_vue_back.mapper.QuizMapper;
import com.rinesarusinovci.online_quizzes_vue_back.repositories.QuestionRepository;
import com.rinesarusinovci.online_quizzes_vue_back.repositories.QuizRepository;
import com.rinesarusinovci.online_quizzes_vue_back.services.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {
    private final QuizMapper quizMapper;
    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;

    @Override
    public List<QuizDto> findAll() {
        return quizMapper.toDtos(quizRepository.findAll());
    }

    @Override
    public QuizDto findById(Long aLong) {
        return quizMapper.toDto(quizRepository.findById(aLong).orElse(null));
    }

    @Override
    public QuizDto add(QuizDto model) {
        return save(model);
    }

    @Override
    public QuizDto modify(Long id, QuizDto quizDto) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));


         quizRepository.save(quiz);
          return quizMapper.toDto(quiz);
    }



    @Override
    public void removeById(Long aLong) {
        List<Question> questions = questionRepository.findByQuizId(aLong);

        // Delete all questions
        questionRepository.deleteAll(questions);

        // Now delete the quiz
        quizRepository.deleteById(aLong);
    }

    private QuizDto save(QuizDto model) {
        var quizEntity = quizMapper.toEntity(model);
        var savedQuiz = quizRepository.save(quizEntity);
        return quizMapper.toDto(savedQuiz);
    }
}
