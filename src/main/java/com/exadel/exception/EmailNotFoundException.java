package com.exadel.exception;

public class EmailNotFoundException extends RuntimeException {

    private String email;

    public EmailNotFoundException(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
