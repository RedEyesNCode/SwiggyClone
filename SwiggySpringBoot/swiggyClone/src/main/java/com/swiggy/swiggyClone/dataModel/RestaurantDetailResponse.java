package com.swiggy.swiggyClone.dataModel;

import java.util.List;

public class RestaurantDetailResponse extends StatusCodeModel {
    public RestaurantDetailResponse(String status, int code, String message) {
        super(status, code, message);
    }

    public RestaurantDetailResponse(String status, int code) {
        super(status, code);
    }

    private RestaurantDetailTable data;

    public RestaurantDetailResponse(String status, int code, String message, RestaurantDetailTable data) {
        super(status, code, message);
        this.data = data;
    }

    public RestaurantDetailResponse(String status, int code, RestaurantDetailTable data) {
        super(status, code);
        this.data = data;
    }

    public RestaurantDetailTable getData() {
        return data;
    }

    public void setData(RestaurantDetailTable data) {
        this.data = data;
    }
}
