package com.swiggy.swiggyClone.dataModel.placeOrder;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table
@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
public class PaymentDetailTable {

    @Id
    @SequenceGenerator(name = "order_data_sequence", sequenceName = "order_data_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_data_sequence")
    private Long paymentId;

    private Long orderId;
    private Long userId;
    private double amount;
    private String provider;
    private String orderStatus;

    private String createdAt;

    public PaymentDetailTable(Long paymentId, Long orderId, Long userId, double amount, String provider, String orderStatus, String createdAt) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.userId = userId;
        this.amount = amount;
        this.provider = provider;
        this.orderStatus = orderStatus;
        this.createdAt = createdAt;
    }

    public PaymentDetailTable(Long paymentId, Long orderId, double amount, String provider, String orderStatus, String createdAt) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.amount = amount;
        this.provider = provider;
        this.orderStatus = orderStatus;
        this.createdAt = createdAt;
    }

    public PaymentDetailTable(Long orderId, double amount, String provider, String orderStatus, String createdAt) {
        this.orderId = orderId;
        this.amount = amount;
        this.provider = provider;
        this.orderStatus = orderStatus;
        this.createdAt = createdAt;
    }

    public PaymentDetailTable() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
