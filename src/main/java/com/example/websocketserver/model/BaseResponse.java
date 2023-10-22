package com.example.websocketserver.model;

public class BaseResponse {
    public int CODE;
    public String MESSAGE;
    public Object RESULT;

    public BaseResponse() {}

    public BaseResponse(int code, String message, Object result) {
        this.CODE = code;
        this.MESSAGE = message;
        this.RESULT = result;
    }
}
