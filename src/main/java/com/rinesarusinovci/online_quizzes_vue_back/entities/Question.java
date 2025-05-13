package com.rinesarusinovci.online_quizzes_vue_back.entities;


import com.rinesarusinovci.online_quizzes_vue_back.enums.QuestionType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "question")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 2000)
    private String text;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private QuestionType questionType;

    @Column(nullable = false)
    private double points;

    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;



    @Column(name = "choice", nullable = false)
    private List<String> choices;

    @Column(nullable = false)
    private boolean correct;
}
