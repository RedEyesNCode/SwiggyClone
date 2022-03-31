package com.swiggy.swiggyClone.dataModel.address;


import javax.persistence.*;

@Entity
@Table
public class AddressTable {

    @Id
    @SequenceGenerator(name = "address_data_sequence", sequenceName = "address_data_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_data_sequence")
    private Long addressId;


    private int userId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String apartMent;
    private String address;
    private String postalCode;
    private String city;


    public AddressTable(Long addressId, int userId, String firstName, String lastName, String phoneNumber, String apartMent, String address, String postalCode, String city) {
        this.addressId = addressId;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.apartMent = apartMent;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
    }

    public AddressTable(int userId, String firstName, String lastName, String phoneNumber, String apartMent, String address, String postalCode, String city) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.apartMent = apartMent;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
    }



    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
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
