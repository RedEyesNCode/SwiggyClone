package com.swiggy.swiggyClone.dataModel;

public class LoginResponse extends StatusCodeModel {
    private Long userId;
    private SignupModel signupModel;

    public LoginResponse(String status, int code, String message, Long userId, SignupModel signupModel) {
        super(status, code, message);
        this.userId = userId;
        this.signupModel = signupModel;
    }

    public LoginResponse(String status, int code, String message, SignupModel signupModel) {
        super(status, code, message);
        this.signupModel = signupModel;
    }

    public LoginResponse(String status, int code, SignupModel signupModel) {
        super(status, code);
        this.signupModel = signupModel;
    }

    public LoginResponse(String status, int code, Long userId, SignupModel signupModel) {
        super(status, code);
        this.userId = userId;
        this.signupModel = signupModel;
    }

    public LoginResponse(String status, int code, String message, Long userId) {
        super(status, code, message);
        this.userId = userId;
    }

    public LoginResponse(String status, int code, Long userId) {
        super(status, code);
        this.userId = userId;
    }
}
