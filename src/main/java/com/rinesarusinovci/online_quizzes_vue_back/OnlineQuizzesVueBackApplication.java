package com.rinesarusinovci.online_quizzes_vue_back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableScheduling
@EnableMethodSecurity
public class OnlineQuizzesVueBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineQuizzesVueBackApplication.class, args);
    }

}
