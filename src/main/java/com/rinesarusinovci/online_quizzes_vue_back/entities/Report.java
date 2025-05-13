package com.rinesarusinovci.online_quizzes_vue_back.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reports")
@Builder
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    @OneToOne(mappedBy = "report")
//    private Quiz quiz;
    @Column(nullable = false)
    @NotNull(message = "Average score is required")
    @Positive
    private double averageScore;

    @Column(nullable = false)
    @NotNull(message = "Highest score is required")
    @Positive
    private double highestScore;

    @Column(nullable = false)
    @NotNull(message = "Lowest score is required")
    @PastOrPresent(message = "Created at must be in the past or present")
    private LocalDate createdAt;
}
