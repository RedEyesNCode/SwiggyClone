package com.swiggy.swiggyClone.dataModel.cart;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;


@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CartListData implements Serializable {

    private Long orderId;
    private CartData data;
    private ProductData product;


    public CartListData() {
    }

    public CartListData(Long orderId, CartData data, ProductData product) {
        this.orderId = orderId;
        this.data = data;
        this.product = product;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public CartData getData() {
        return data;
    }

    public void setData(CartData data) {
        this.data = data;
    }

    public ProductData getProduct() {
        return product;
    }

    public void setProduct(ProductData product) {
        this.product = product;
    }
}
