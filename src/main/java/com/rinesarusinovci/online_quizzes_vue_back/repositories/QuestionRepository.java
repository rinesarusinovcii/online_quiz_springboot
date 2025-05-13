package com.rinesarusinovci.online_quizzes_vue_back.repositories;


import com.rinesarusinovci.online_quizzes_vue_back.entities.Question;
import com.rinesarusinovci.online_quizzes_vue_back.enums.QuestionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Override
    Optional<Question> findById(Long id);

    Optional<Question> findByText(String text);

    Optional<Question> findByQuestionType(QuestionType questionType);

    List<Question> findByQuizId(Long quizId);
}
