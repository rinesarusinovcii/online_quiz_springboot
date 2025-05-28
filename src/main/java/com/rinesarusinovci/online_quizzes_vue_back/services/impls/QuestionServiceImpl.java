package com.rinesarusinovci.online_quizzes_vue_back.services.impls;


import com.rinesarusinovci.online_quizzes_vue_back.dto.QuestionDto;
import com.rinesarusinovci.online_quizzes_vue_back.entities.Question;
import com.rinesarusinovci.online_quizzes_vue_back.mapper.QuestionMapper;
import com.rinesarusinovci.online_quizzes_vue_back.repositories.QuestionRepository;
import com.rinesarusinovci.online_quizzes_vue_back.repositories.QuizRepository;
import com.rinesarusinovci.online_quizzes_vue_back.services.QuestionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionMapper questionMapper;
    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;


    @Override
    public List<QuestionDto> findAll() {
        return questionMapper.toDtos(questionRepository.findAll());
    }

    @Override
    public QuestionDto findById(Long aLong) {
        return questionMapper.toDto(questionRepository.findById(aLong).orElse(null));
    }

    @Override
    public QuestionDto add(QuestionDto model) {
        return save(model);
    }

    @Override
    public QuestionDto modify(Long aLong, QuestionDto model) {
        if (aLong != model.getId()) {
            throw new IllegalArgumentException("Id does not match");
        }


        if (!questionRepository.existsById(aLong)) {
            throw new EntityNotFoundException("Post with id " + aLong + " not found");
        }
        return save(model);
    }

    @Override
    public void removeById(Long aLong) {
        if (!questionRepository.existsById(aLong)) {
            throw new EntityNotFoundException("Post with id " + aLong + " not found");
        }

        questionRepository.deleteById(aLong);
    }


    private QuestionDto save(QuestionDto model) {
        var questionEntity = questionMapper.toEntity(model);

        // Merr quiz nga baza e të dhënave duke përdorur quizId
        var quiz = quizRepository.findById(model.getQuizId())
                .orElseThrow(() -> new EntityNotFoundException("Quiz with id " + model.getQuizId() + " not found"));

        // Lidh pyetjen me quiz-in
        questionEntity.setQuiz(quiz);

        // Ruaj pyetjen
        var savedQuestion = questionRepository.save(questionEntity);
        return questionMapper.toDto(savedQuestion);
    }

    @Override
    public List<Question> findByQuizId(Long quizId) {
        return questionRepository.findByQuizId(quizId);
    }

}
