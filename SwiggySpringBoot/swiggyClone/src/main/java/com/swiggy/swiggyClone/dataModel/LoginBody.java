package com.swiggy.swiggyClone.dataModel;

public class LoginBody {

    private String number;
    private String password;

    public LoginBody() {
    }

    public LoginBody(String number, String password) {
        this.number = number;
        this.password = password;
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
}
