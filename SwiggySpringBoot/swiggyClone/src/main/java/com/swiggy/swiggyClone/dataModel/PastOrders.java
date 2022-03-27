package com.swiggy.swiggyClone.dataModel;

import javax.persistence.*;


@Entity
@Table
public class PastOrders {

    @Id
    @SequenceGenerator(name = "users_data_sequence", sequenceName = "users_data_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_data_sequence")
    private Long pastOrderId;


    private int userID;
    private String restaurantName;
    private String location;
    private Double orderTotal;
    private String orderStatus;

    public PastOrders(int userID, String restaurantName, String location, Double orderTotal, String orderStatus) {
        this.userID = userID;
        this.restaurantName = restaurantName;
        this.location = location;
        this.orderTotal = orderTotal;
        this.orderStatus = orderStatus;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public PastOrders() {
    }

    public PastOrders(String restaurantName, String location, Double orderTotal, String orderStatus) {
        this.restaurantName = restaurantName;
        this.location = location;
        this.orderTotal = orderTotal;
        this.orderStatus = orderStatus;
    }

    public PastOrders(Long pastOrderId, String restaurantName, String location, Double orderTotal, String orderStatus) {
        this.pastOrderId = pastOrderId;
        this.restaurantName = restaurantName;
        this.location = location;
        this.orderTotal = orderTotal;
        this.orderStatus = orderStatus;
    }

    public Long getPastOrderId() {
        return pastOrderId;
    }

    public void setPastOrderId(Long pastOrderId) {
        this.pastOrderId = pastOrderId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(Double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
