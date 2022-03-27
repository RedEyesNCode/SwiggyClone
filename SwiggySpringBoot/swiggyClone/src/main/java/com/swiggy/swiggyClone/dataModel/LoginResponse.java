package com.swiggy.swiggyClone.dataModel;

public class LoginResponse extends StatusCodeModel {
    private Long userId;
    private SignupModel data;

    public LoginResponse(String status, int code, String message, Long userId, SignupModel signupModel) {
        super(status, code, message);
        this.userId = userId;
        this.data = signupModel;
    }

    public LoginResponse(String status, int code, String message, SignupModel signupModel) {
        super(status, code, message);
        this.data = signupModel;
    }

    public LoginResponse(String status, int code, SignupModel signupModel) {
        super(status, code);
        this.data = signupModel;
    }

    public LoginResponse(String status, int code, Long userId, SignupModel signupModel) {
        super(status, code);
        this.userId = userId;
        this.data = signupModel;
    }

    public LoginResponse(String status, int code, String message, Long userId) {
        super(status, code, message);
        this.userId = userId;
    }

    public LoginResponse(String status, int code, Long userId) {
        super(status, code);
        this.userId = userId;
    }

    public LoginResponse(String status, int code, String message) {
        super(status, code, message);
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public SignupModel getData() {
        return data;
    }

    public void setData(SignupModel data) {
        this.data = data;
    }
}
