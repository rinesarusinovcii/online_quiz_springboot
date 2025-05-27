package com.rinesarusinovci.online_quizzes_vue_back.repositories;


import com.rinesarusinovci.online_quizzes_vue_back.entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

    List<Quiz> findByCreatedById(Long userId);



}
