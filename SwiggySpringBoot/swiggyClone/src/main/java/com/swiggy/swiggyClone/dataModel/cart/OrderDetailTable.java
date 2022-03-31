package com.swiggy.swiggyClone.dataModel.cart;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table
@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
public class OrderDetailTable {


    @Id
    @SequenceGenerator(name = "order_detail_data_sequence", sequenceName = "order_detail_data_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_detail_data_sequence")
    private Long orderDetailId;

    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Long orderId;
    private Long restaurantId;
    private Long productId;
    private String createdAt;

    public OrderDetailTable(Long orderDetailId, Long orderId, Long restaurantId, Long productId, String createdAt) {
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.restaurantId = restaurantId;
        this.productId = productId;
        this.createdAt = createdAt;
    }

    public OrderDetailTable() {
    }

    public OrderDetailTable(Long orderId, Long restaurantId, Long productId, String createdAt) {
        this.orderId = orderId;
        this.restaurantId = restaurantId;
        this.productId = productId;
        this.createdAt = createdAt;
    }

    public Long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
