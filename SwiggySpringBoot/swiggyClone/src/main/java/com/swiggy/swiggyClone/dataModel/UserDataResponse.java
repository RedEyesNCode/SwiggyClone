package com.swiggy.swiggyClone.dataModel;

public class UserDataResponse extends StatusCodeModel {


    private SignupModel data;

    public UserDataResponse(String status, int code, String message, SignupModel data) {
        super(status, code, message);
        this.data = data;
    }

    public SignupModel getData() {
        return data;
    }

    public void setData(SignupModel data) {
        this.data = data;
    }

    public UserDataResponse(String status, int code, SignupModel data) {
        super(status, code);
        this.data = data;
    }

    public UserDataResponse(String status, int code, String message) {
        super(status, code, message);
    }

    public UserDataResponse(String status, int code) {
        super(status, code);
    }
}
