package com.swiggy.swiggyClone.dataModel;

import javax.persistence.*;

@Entity
@Table
public class SignupModel {

    @Id
    @SequenceGenerator(name = "users_data_sequence", sequenceName = "users_data_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_data_sequence")
    private Long id;

    private String userEmail;
    private String number;
    private String password;
    private String userName;

    public SignupModel(Long id, String userEmail, String number, String password, String userName) {
        this.id = id;
        this.userEmail = userEmail;
        this.number = number;
        this.password = password;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public SignupModel(Long id, String userEmail, String number, String password) {
        this.id = id;
        this.userEmail = userEmail;
        this.number = number;
        this.password = password;
    }

    public SignupModel(String userEmail, String number, String password, String userName) {
        this.userEmail = userEmail;
        this.number = number;
        this.password = password;
        this.userName = userName;
    }

    public SignupModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
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