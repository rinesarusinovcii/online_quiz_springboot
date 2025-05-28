    package com.rinesarusinovci.online_quizzes_vue_back.entities;

    import jakarta.persistence.*;
    import jakarta.validation.constraints.*;
    import lombok.*;

    import java.time.LocalDate;
    import java.util.List;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Entity
    @Table(name = "quizzes")
    @Builder
    public class Quiz {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @PositiveOrZero(message = "Id must be a positive number")
        private long id;

        @Column(nullable = false, length = 50)
        @NotNull(message = "Title is required")
        @NotBlank(message = "Title is required")
        @Size(min = 10, max = 50, message = "Title must be between 10 and 50 characters")
        private String title;

        @Column(length = 1000)
        @Size(min = 10, max = 1000, message = "Description must be between 10 and 1000 characters")
        private String description;

        @Column(nullable = false, length = 20)
        @NotNull(message = "Category is required")
        @NotBlank(message = "Category is required")
        @Size(min = 5, max = 20, message = "Category must be between 5 and 20 characters")
        private String category;



        @Column(nullable = false)
        @NotNull(message = "Created at is required")
        @PastOrPresent(message = "Created at must be in the past or present")
        private LocalDate createdAt;

        @Column(nullable = false)
        @NotNull(message = "Time limit is required")
        @Positive(message = "Time limit must be a positive number")
        private int timeLimit;

        @ManyToOne
        @JoinColumn(name = "user_id")
        private User createdBy;

        @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
        private List<Question> questions;

        @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @Column(nullable = false)
        private List<Result> results;



//        @OneToOne(cascade = CascadeType.ALL)
//        @JoinColumn(name = "report_id", referencedColumnName = "id")
//        private Report report;
//
//        @OneToMany(mappedBy = "quiz")
//        private List<Result> results;



    }
