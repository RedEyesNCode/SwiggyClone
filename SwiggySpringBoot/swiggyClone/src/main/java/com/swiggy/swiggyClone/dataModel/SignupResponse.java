package com.swiggy.swiggyClone.dataModel;

public class SignupResponse {

    private int code;
    private String status;
    private  String message;
    private int id;

    public SignupResponse(int code, String status, String message, int id) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.id = id;
    }

    public SignupResponse() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
