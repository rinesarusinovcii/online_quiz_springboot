package com.rinesarusinovci.online_quizzes_vue_back.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "results")
@Builder
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    @Column(nullable = false)
    @NotNull
    @PositiveOrZero(message = "Score must be a positive number")
    private double score;

    @Column(nullable = false)
    @NotNull
    @PositiveOrZero(message = "Correct answers must be a positive number")
    private int correctAnswers;

    @Column(nullable = false)
    @NotNull
    @PositiveOrZero(message = "Wrong answers must be a positive number")
    private int wrongAnswers;

    @Column(nullable = false)
    @NotNull
    private boolean isPassed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
