package com.project.app.common.exception;

public class GlobalErrorResponse {

    private final int code;
    private final String message;

    public GlobalErrorResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
