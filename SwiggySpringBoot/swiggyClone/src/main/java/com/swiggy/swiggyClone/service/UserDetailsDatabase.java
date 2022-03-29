package com.swiggy.swiggyClone.service;

import com.swiggy.swiggyClone.dataModel.SignupModel;

public class UserDetailsDatabase {

    private String number;
    private String password;

    private SignupModel signupModel;

    public UserDetailsDatabase() {
    }

    public UserDetailsDatabase(String number, String password, SignupModel signupModel) {
        this.number = number;
        this.password = password;
        this.signupModel = signupModel;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SignupModel getSignupModel() {
        return signupModel;
    }

    public void setSignupModel(SignupModel signupModel) {
        this.signupModel = signupModel;
    }
}
