package com.swiggy.swiggyClone.dataModel;

import java.util.List;

public class UsersJson {
    private int code;
    private String status;
    private List<SignupModel> users;

    public UsersJson() {
    }

    public UsersJson(int code, String status, List<SignupModel> signupModels) {
        this.code = code;
        this.status = status;
        this.users = signupModels;
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

    public List<SignupModel> getUsers() {
        return users;
    }

    public void setUsers(List<SignupModel> users) {
        this.users = users;
    }
}
