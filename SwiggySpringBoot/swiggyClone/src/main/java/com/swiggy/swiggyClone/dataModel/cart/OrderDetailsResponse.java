package com.swiggy.swiggyClone.dataModel.cart;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.swiggy.swiggyClone.dataModel.RestaurantDetailTable;
import com.swiggy.swiggyClone.dataModel.StatusCodeModel;
import com.swiggy.swiggyClone.dataModel.placeOrder.PaymentDetailTable;

import java.io.Serializable;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OrderDetailsResponse extends StatusCodeModel implements Serializable {


    public OrderDetailsResponse(String status, int code, String message) {
        super(status, code, message);
    }

    public OrderDetailsResponse(String status, int code) {
        super(status, code);
    }

    private Long orderId;
    private RestaurantDetailTable restaurant;
    private ProductData product;
    private PaymentDetailTable paymentInfo;

    public OrderDetailsResponse(String status, int code, String message, Long orderId, RestaurantDetailTable restaurant, ProductData product, PaymentDetailTable paymentInfo) {
        super(status, code, message);
        this.orderId = orderId;
        this.restaurant = restaurant;
        this.product = product;
        this.paymentInfo = paymentInfo;
    }

    public OrderDetailsResponse(String status, int code, Long orderId, RestaurantDetailTable restaurant, ProductData product, PaymentDetailTable paymentInfo) {
        super(status, code);
        this.orderId = orderId;
        this.restaurant = restaurant;
        this.product = product;
        this.paymentInfo = paymentInfo;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public RestaurantDetailTable getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantDetailTable restaurant) {
        this.restaurant = restaurant;
    }

    public ProductData getProduct() {
        return product;
    }

    public void setProduct(ProductData product) {
        this.product = product;
    }

    public PaymentDetailTable getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(PaymentDetailTable paymentInfo) {
        this.paymentInfo = paymentInfo;
    }
}
