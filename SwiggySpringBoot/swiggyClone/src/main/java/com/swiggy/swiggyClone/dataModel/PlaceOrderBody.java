package com.swiggy.swiggyClone.dataModel;

public class PlaceOrderBody {

    private Long orderId;
    private Long userId;
    private Long addressId
    private String orderName;

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
