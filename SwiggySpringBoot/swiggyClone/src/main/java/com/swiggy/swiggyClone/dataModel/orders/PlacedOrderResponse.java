package com.swiggy.swiggyClone.dataModel.orders;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.swiggy.swiggyClone.dataModel.StatusCodeModel;
import com.swiggy.swiggyClone.dataModel.cart.CartData;
import com.swiggy.swiggyClone.dataModel.cart.ProductData;
import com.swiggy.swiggyClone.dataModel.placeOrder.PaymentDetailTable;

import java.io.Serializable;


@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PlacedOrderResponse extends StatusCodeModel implements Serializable {


    public PlacedOrderResponse(String status, int code, String message) {
        super(status, code, message);
    }

    public PlacedOrderResponse(String status, int code) {
        super(status, code);
    }

    private Long orderId;
    private CartData data;
    private ProductData product;
    private PaymentDetailTable paymentInfo;

    public PlacedOrderResponse(String status, int code, String message, Long orderId, CartData data, ProductData product, PaymentDetailTable paymentInfo) {
        super(status, code, message);
        this.orderId = orderId;
        this.data = data;
        this.product = product;
        this.paymentInfo = paymentInfo;
    }

    public PlacedOrderResponse(String status, int code, Long orderId, CartData data, ProductData product, PaymentDetailTable paymentInfo) {
        super(status, code);
        this.orderId = orderId;
        this.data = data;
        this.product = product;
        this.paymentInfo = paymentInfo;
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

    public PaymentDetailTable getPaymentDetailTable() {
        return paymentInfo;
    }

    public void setPaymentDetailTable(PaymentDetailTable paymentInfo) {
        this.paymentInfo = paymentInfo;
    }
}
