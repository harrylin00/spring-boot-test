package com.example.demo.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND("no questions found");

    @Override
    public String getMessage() {
        return this.message;
    }

    private String message;

    CustomizeErrorCode(String message) {
        this.message = message;
    }

}
