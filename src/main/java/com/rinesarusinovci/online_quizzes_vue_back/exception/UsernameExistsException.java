package com.rinesarusinovci.online_quizzes_vue_back.exception;

public class UsernameExistsException extends RuntimeException{
    public UsernameExistsException() {
        super("Username already exists");
    }

    public UsernameExistsException(String message) {
        super(message);
    }
}
