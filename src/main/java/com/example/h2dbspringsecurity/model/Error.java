package com.example.h2dbspringsecurity.model;

public class Error {

    private String code;
    private String message;

    public Error() {}

    public Error(String code) {
        this.code = code;
    }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
