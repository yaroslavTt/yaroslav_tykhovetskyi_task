package com.example.yaroslav_tykhovetskyi_tech_task.exception;

public class InvalidRequestException extends RuntimeException{
    public InvalidRequestException(String message) {
        super(message);
    }
}
