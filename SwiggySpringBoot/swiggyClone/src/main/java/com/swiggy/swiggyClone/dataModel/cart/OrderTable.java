package com.swiggy.swiggyClone.dataModel.cart;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table
@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
public class OrderTable {

    @Id
    @SequenceGenerator(name = "order_data_sequence", sequenceName = "order_data_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_data_sequence")
    private Long orderId;

    private Long userId;

    public OrderTable(Long orderId, Long userId) {
        this.orderId = orderId;
        this.userId = userId;
    }

    public OrderTable() {
    }

    public OrderTable(Long userId) {
        this.userId = userId;
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
}
