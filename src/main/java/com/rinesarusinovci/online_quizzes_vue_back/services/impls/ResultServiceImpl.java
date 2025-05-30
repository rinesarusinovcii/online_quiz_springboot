package com.rinesarusinovci.online_quizzes_vue_back.services.impls;

import com.rinesarusinovci.online_quizzes_vue_back.dto.ResultDto;
import com.rinesarusinovci.online_quizzes_vue_back.entities.Question;
import com.rinesarusinovci.online_quizzes_vue_back.entities.Quiz;
import com.rinesarusinovci.online_quizzes_vue_back.entities.User;
import com.rinesarusinovci.online_quizzes_vue_back.mapper.ResultMapper;
import com.rinesarusinovci.online_quizzes_vue_back.repositories.QuizRepository;
import com.rinesarusinovci.online_quizzes_vue_back.repositories.ResultRepository;
import com.rinesarusinovci.online_quizzes_vue_back.repositories.UserRepository;
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
    private final QuizRepository quizRepository;
    private final UserRepository userRepository;

    private final double passingScore = 30;

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
    public ResultDto saveCalculatedResult(List<Question> questions, Map<Long, Long> userAnswers, Long quizId,Long userId) {
        ResultDto result = calculateResult(questions, userAnswers, quizId,userId);
        return add(result);
    }

    public ResultDto calculateResult(List<Question> questions, Map<Long, Long> userAnswers, Long quizId,Long userId) {
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

        ResultDto result = new ResultDto();
        result.setScore(score);
        result.setCorrectAnswers(correctAnswers);
        result.setWrongAnswers(wrongAnswers);
        result.setPassed(isPassed);
        result.setQuizId(quizId);
        result.setUserId(userId);


        return result;
    }

    private ResultDto save(ResultDto model) {
        if (model.getUserId() == null) {
            throw new IllegalArgumentException("Username must not be null or empty");
        }
        System.out.println("Saving result for user: " + model.getUserId() + " quizId: " + model.getQuizId());
        Quiz quiz = quizRepository.findById(model.getQuizId())
                .orElseThrow(() -> new EntityNotFoundException("Quiz with id " + model.getQuizId() + " not found"));

        User user = userRepository.findById(model.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        var resultEntity = resultMapper.toEntity(model);
        resultEntity.setQuiz(quiz);
        resultEntity.setUser(user);

        var savedResult = resultRepository.save(resultEntity);
        return resultMapper.toDto(savedResult);
    }


}
