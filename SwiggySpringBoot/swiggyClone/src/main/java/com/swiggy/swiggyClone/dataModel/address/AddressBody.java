package com.swiggy.swiggyClone.dataModel.address;

public class AddressBody {


    private int userId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String apartMent;
    private String address;
    private String postalCode;
    private String city;

    public AddressBody(int userId, String firstName, String lastName, String phoneNumber, String apartMent, String address, String postalCode, String city) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.apartMent = apartMent;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getApartMent() {
        return apartMent;
    }

    public void setApartMent(String apartMent) {
        this.apartMent = apartMent;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
