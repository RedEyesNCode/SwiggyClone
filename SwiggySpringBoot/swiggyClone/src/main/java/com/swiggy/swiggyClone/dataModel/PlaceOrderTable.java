package com.swiggy.swiggyClone.dataModel;


import javax.persistence.*;

@Entity
@Table
public class PlaceOrderTable {

    @Id
    @SequenceGenerator(name = "placeorder_sequence_table", sequenceName = "placeorder_sequence_table",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "placeorder_sequence_table")
    private Long id;

    private Long orderId;
    private Long addressId;
    private Long userId;
    private String deliveryBoyName;



    public PlaceOrderTable() {
    }

    public PlaceOrderTable(Long id, Long orderId, Long addressId, Long userId) {
        this.id = id;
        this.orderId = orderId;
        this.addressId = addressId;
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public PlaceOrderTable(Long orderId, Long addressId, Long userId) {
        this.orderId = orderId;
        this.addressId = addressId;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }
}
