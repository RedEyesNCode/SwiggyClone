package com.swiggy.swiggyClone.dataModel;

public class PlaceOrderBody {

    private Long orderId;
    private Long userId;
    private Long addressId;
    private String orderName;
    private String provider;
    private String orderStatus;
    private Double amount;

    private String customerName;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public PlaceOrderBody() {
    }

    public PlaceOrderBody(Long orderId, Long userId, Long addressId, String orderName, String provider, String orderStatus, Double amount, String customerName) {
        this.orderId = orderId;
        this.userId = userId;
        this.addressId = addressId;
        this.orderName = orderName;
        this.provider = provider;
        this.amount= amount;
        this.orderStatus = orderStatus;
        this.customerName = customerName;
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

    public PlaceOrderBody(Long orderId, Long userId, Long addressId, String orderName) {
        this.orderId = orderId;
        this.userId = userId;
        this.addressId = addressId;
        this.orderName = orderName;
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

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }
}
