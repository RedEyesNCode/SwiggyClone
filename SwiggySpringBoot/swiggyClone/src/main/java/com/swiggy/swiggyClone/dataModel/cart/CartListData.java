package com.swiggy.swiggyClone.dataModel.cart;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.swiggy.swiggyClone.dataModel.placeOrder.PaymentDetailTable;

import java.io.Serializable;


@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CartListData implements Serializable {

    private Long orderId;
    private CartData data;
    private ProductData product;
    private PaymentDetailTable paymentDetail;


    public CartListData() {
    }

    public CartListData(Long orderId, CartData data, ProductData product, PaymentDetailTable paymentDetail) {
        this.orderId = orderId;
        this.data = data;
        this.product = product;
        this.paymentDetail = paymentDetail;
    }

    public PaymentDetailTable getPaymentDetail() {
        return paymentDetail;
    }

    public void setPaymentDetail(PaymentDetailTable paymentDetail) {
        this.paymentDetail = paymentDetail;
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
