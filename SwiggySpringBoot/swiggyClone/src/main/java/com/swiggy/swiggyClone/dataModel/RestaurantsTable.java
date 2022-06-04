package com.swiggy.swiggyClone.dataModel;


import javax.persistence.*;

@Entity
@Table
public class RestaurantsTable {

    @Id
    @SequenceGenerator(name = "restaurant_data_sequence", sequenceName = "restaurant_data_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restaurant_data_sequence")
    private Long restaurantId;

    private String restaurantName;
    private Double rating;
    private String deliveryTime;
    private Double discount;
    private boolean isSwiggyOne;

    private String restaurantImage;

    public String getRestaurantImage() {
        return restaurantImage;
    }

    public void setRestaurantImage(String restaurantImage) {
        this.restaurantImage = restaurantImage;
    }

    public RestaurantsTable(String restaurantName, Double rating, String deliveryTime, Double discount, boolean isSwiggyOne, String restaurantImage) {
        this.restaurantName = restaurantName;
        this.rating = rating;
        this.deliveryTime = deliveryTime;
        this.discount = discount;
        this.isSwiggyOne = isSwiggyOne;
        this.restaurantImage = restaurantImage;

    }

    public RestaurantsTable(Long id, String restaurantName, Double rating, String deliveryTime, Double discount) {
        this.restaurantId = id;
        this.restaurantName = restaurantName;
        this.rating = rating;
        this.deliveryTime = deliveryTime;
        this.discount = discount;
    }

    public RestaurantsTable() {
    }

    public RestaurantsTable(String restaurantName, Double rating, String deliveryTime, Double discount) {
        this.restaurantName = restaurantName;
        this.rating = rating;
        this.deliveryTime = deliveryTime;
        this.discount = discount;
    }

    public boolean isSwiggyOne() {
        return isSwiggyOne;
    }

    public void setSwiggyOne(boolean swiggyOne) {
        isSwiggyOne = swiggyOne;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}
