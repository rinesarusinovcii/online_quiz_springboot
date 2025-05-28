package com.rinesarusinovci.online_quizzes_vue_back.services.impls;

import com.rinesarusinovci.online_quizzes_vue_back.dto.ResultDto;
import com.rinesarusinovci.online_quizzes_vue_back.entities.Quiz;
import com.rinesarusinovci.online_quizzes_vue_back.entities.Question;
import com.rinesarusinovci.online_quizzes_vue_back.mapper.ResultMapper;
import com.rinesarusinovci.online_quizzes_vue_back.repositories.QuizRepository;
import com.rinesarusinovci.online_quizzes_vue_back.repositories.ResultRepository;
import com.rinesarusinovci.online_quizzes_vue_back.services.ResultService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {

    private final ResultMapper resultMapper;
    private final ResultRepository resultRepository;
    private final QuizRepository quizRepository; // Shto kete

    private final double passingScore = 50.0;

    @Override
    public List<ResultDto> findAll() {
        return resultMapper.toDtos(resultRepository.findAll());
    }

    @Override
    public ResultDto findById(Long aLong) {
        return resultMapper.toDto(resultRepository.findById(aLong).orElse(null));
    }

    @Override
    public ResultDto add(ResultDto model) {
        return save(model);
    }

    @Override
    public ResultDto modify(Long aLong, ResultDto model) {
        if (!aLong.equals(model.getId())) {
            throw new IllegalArgumentException("Id does not match");
        }

        if (!resultRepository.existsById(aLong)) {
            throw new EntityNotFoundException("Result with id " + aLong + " not found");
        }
        return save(model);
    }

    @Override
    public void removeById(Long aLong) {
        if (!resultRepository.existsById(aLong)) {
            throw new EntityNotFoundException("Result with id " + aLong + " not found");
        }
        resultRepository.deleteById(aLong);
    }

    @Override
    public ResultDto saveCalculatedResult(List<Question> questions, Map<Long, Long> userAnswers, Long quizId) {
        ResultDto result = calculateResult(questions, userAnswers, quizId);
        return add(result);
    }

    public ResultDto calculateResult(List<Question> questions, Map<Long, Long> userAnswers, Long quizId) {
        double score = 0;
        int correctAnswers = 0;
        int wrongAnswers = 0;

        for (Question q : questions) {
            Long selectedChoiceId = userAnswers.get(q.getId());
            if (selectedChoiceId != null) {
                var correctChoice = q.getChoices().stream()
                        .filter(choice -> choice.isCorrect())
                        .findFirst()
                        .orElse(null);

                if (correctChoice != null && correctChoice.getId().equals(selectedChoiceId)) {
                    score += q.getPoints();
                    correctAnswers++;
                } else {
                    wrongAnswers++;
                }
            } else {
                wrongAnswers++;
            }
        }

        boolean isPassed = score >= passingScore;

        return new ResultDto(0, quizId, score, correctAnswers, wrongAnswers, isPassed);
    }

    private ResultDto save(ResultDto model) {
        // Ngarko quiz-in nga DB:
        Quiz quiz = quizRepository.findById(model.getQuizId())
                .orElseThrow(() -> new EntityNotFoundException("Quiz with id " + model.getQuizId() + " not found"));

        var resultEntity = resultMapper.toEntity(model);
        resultEntity.setQuiz(quiz);  // lidhe quiz me result

        var savedResult = resultRepository.save(resultEntity);
        return resultMapper.toDto(savedResult);
    }
}
