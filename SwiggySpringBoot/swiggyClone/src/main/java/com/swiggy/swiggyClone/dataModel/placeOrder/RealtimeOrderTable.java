package com.swiggy.swiggyClone.dataModel.placeOrder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table
@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
public class RealtimeOrderTable {

    @Id
    @SequenceGenerator(name = "order_data_sequence", sequenceName = "order_data_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_data_sequence")
    private Long placeOrderid;

    private Long orderId;
    private Long userId;
    private double amount;
    private String provider;
    private String orderStatus;
    private String customerName;
    private String createdAt;

    public RealtimeOrderTable() {
    }

    public RealtimeOrderTable(Long placeOrderid, Long orderId, Long userId, double amount, String provider, String orderStatus, String customerName, String createdAt) {
        this.placeOrderid = placeOrderid;
        this.orderId = orderId;
        this.userId = userId;
        this.amount = amount;
        this.provider = provider;
        this.orderStatus = orderStatus;
        this.customerName = customerName;
        this.createdAt = createdAt;
    }

    public RealtimeOrderTable(Long orderId, Long userId, double amount, String provider, String orderStatus, String customerName, String createdAt) {
        this.orderId = orderId;
        this.userId = userId;
        this.amount = amount;
        this.provider = provider;
        this.orderStatus = orderStatus;
        this.customerName = customerName;
        this.createdAt = createdAt;
    }

    public Long getPlaceOrderid() {
        return placeOrderid;
    }

    public void setPlaceOrderid(Long placeOrderid) {
        this.placeOrderid = placeOrderid;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
