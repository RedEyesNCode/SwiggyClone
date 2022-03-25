package com.swiggy.swiggyClone.dataModel;

import java.util.List;

public class RestaurantListResponse extends StatusCodeModel {
    public RestaurantListResponse(String status, int code, String message) {
        super(status, code, message);
    }

    public RestaurantListResponse(String status, int code) {
        super(status, code);
    }

    private List<RestaurantsTable> data;

    public RestaurantListResponse(String status, int code, String message, List<RestaurantsTable> data) {
        super(status, code, message);
        this.data = data;
    }

    public RestaurantListResponse(String status, int code, List<RestaurantsTable> data) {
        super(status, code);
        this.data = data;
    }

    public List<RestaurantsTable> getData() {
        return data;
    }

    public void setData(List<RestaurantsTable> data) {
        this.data = data;
    }
}
