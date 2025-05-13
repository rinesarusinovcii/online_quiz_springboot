package com.rinesarusinovci.online_quizzes_vue_back.exception;

public class EmailExistsException extends RuntimeException {
    public EmailExistsException(String message) {
        super(message);
    }

    public EmailExistsException() {
        super("Email already exists");
    }
}
