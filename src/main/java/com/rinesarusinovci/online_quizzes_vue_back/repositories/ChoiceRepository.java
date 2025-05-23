package com.rinesarusinovci.online_quizzes_vue_back.repositories;

import com.rinesarusinovci.online_quizzes_vue_back.entities.Choice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChoiceRepository extends JpaRepository<Choice, Long> {

    Optional<Choice> findByCorrect(boolean correct);

    Choice findCorrectChoiceByQuestionId(Long questionId);
}
