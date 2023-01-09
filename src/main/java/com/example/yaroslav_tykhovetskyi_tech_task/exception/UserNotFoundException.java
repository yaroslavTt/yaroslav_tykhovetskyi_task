package com.example.yaroslav_tykhovetskyi_tech_task.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}