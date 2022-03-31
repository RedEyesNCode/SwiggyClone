package com.swiggy.swiggyClone.dataModel.cart;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.swiggy.swiggyClone.dataModel.StatusCodeModel;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CartResponse extends StatusCodeModel implements Serializable {
    private List<CartListData> cart;

    public CartResponse(String status, int code, String message, List<CartListData> cart) {
        super(status, code, message);
        this.cart = cart;
    }


    public CartResponse(String status, int code, List<CartListData> cart) {
        super(status, code);
        this.cart = cart;
    }

    public List<CartListData> getCart() {
        return cart;
    }

    public void setCart(List<CartListData> cart) {
        this.cart = cart;
    }
}
