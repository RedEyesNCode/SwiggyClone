package com.swiggy.swiggyClone.dataModel.cart;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.swiggy.swiggyClone.dataModel.RestaurantDetailTable;

import java.io.Serializable;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CartData implements Serializable {

    private Long restaurantId;
    private RestaurantDetailTable restaurantDetail;

    public CartData(Long restaurantId, RestaurantDetailTable restaurantDetail) {
        this.restaurantId = restaurantId;
        this.restaurantDetail = restaurantDetail;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public RestaurantDetailTable getRestaurantDetail() {
        return restaurantDetail;
    }

    public void setRestaurantDetail(RestaurantDetailTable restaurantDetail) {
        this.restaurantDetail = restaurantDetail;
    }
}
