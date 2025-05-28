package com.rinesarusinovci.online_quizzes_vue_back.services.impls;


import com.rinesarusinovci.online_quizzes_vue_back.dto.QuestionDto;
import com.rinesarusinovci.online_quizzes_vue_back.dto.QuizDto;
import com.rinesarusinovci.online_quizzes_vue_back.dto.QuizResultDto;
import com.rinesarusinovci.online_quizzes_vue_back.dto.QuizSubmissionDto;
import com.rinesarusinovci.online_quizzes_vue_back.entities.Question;
import com.rinesarusinovci.online_quizzes_vue_back.entities.Quiz;
import com.rinesarusinovci.online_quizzes_vue_back.entities.User;
import com.rinesarusinovci.online_quizzes_vue_back.mapper.QuizMapper;
import com.rinesarusinovci.online_quizzes_vue_back.repositories.ChoiceRepository;
import com.rinesarusinovci.online_quizzes_vue_back.repositories.QuestionRepository;
import com.rinesarusinovci.online_quizzes_vue_back.repositories.QuizRepository;
import com.rinesarusinovci.online_quizzes_vue_back.security.AppUserDetails;
import com.rinesarusinovci.online_quizzes_vue_back.services.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.getPrincipal() instanceof AppUserDetails) {
            AppUserDetails userDetails = (AppUserDetails) auth.getPrincipal();
            User currentUser = userDetails.getUser();
            quiz.setTitle(quizDto.getTitle());
            quiz.setDescription(quizDto.getDescription());
            quiz.setCategory(quizDto.getCategory());
            quiz.setTimeLimit(quizDto.getTimeLimit());
            quiz.setCreatedAt(quizDto.getCreatedAt());
            quiz.setCreatedBy(currentUser);
            quizRepository.save(quiz);
            return quizMapper.toDto(quiz);
        } else {
            throw new RuntimeException("User not authenticated");
        }
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

    @Override
    public QuizResultDto evaluateSubmission(Long quizId, QuizSubmissionDto submission) {
        // Merr kuizin nga databaza
        QuizDto quiz = findById(quizId);

        int totalQuestions = quiz.getQuestions().size();
        int correctAnswers = 0;

        for (QuestionDto question : quiz.getQuestions()) {
            String submittedChoiceStr = submission.getAnswers().get(question.getId().toString());

            if (submittedChoiceStr == null) {
                continue; // Skip if no answer submitted for this question
            }

            try {
                Long submittedChoiceId = Long.parseLong(submittedChoiceStr);

                boolean isCorrect = question.getChoices().stream()
                        .anyMatch(choice -> choice.getId().equals(submittedChoiceId) && choice.isCorrect());

                if (isCorrect) {
                    correctAnswers++;
                }
            } catch (NumberFormatException e) {
                // Nëse vlera nuk është numër valid, e kalojmë këtë pyetje
                continue;
            }
        }

        return new QuizResultDto(totalQuestions, correctAnswers);
    }

    @Override
    public List<QuizDto> getQuizzesByUserId(Long userId) {
        List<Quiz> quizzes = quizRepository.findByCreatedById(userId);
        return quizzes.stream()
                .map(quizMapper::toDto)
                .collect(Collectors.toList());
    }
}
