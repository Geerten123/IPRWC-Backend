package com.example.iprwcbackendcode.model;

import org.springframework.http.HttpStatus;

public class ApiResponse<Type> {

    private HttpStatus httpStatus;
    private Type payload;
    private String message;

    public ApiResponse(HttpStatus httpStatus, Type payload) {
        this.httpStatus = httpStatus;
        this.payload = payload;
    }

    public ApiResponse(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public ApiResponse(HttpStatus httpStatus, Type payload, String message) {
        this.httpStatus = httpStatus;
        this.payload = payload;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public Type getPayload() {
        return payload;
    }

    public void setPayload(Type payload) {
        this.payload = payload;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
