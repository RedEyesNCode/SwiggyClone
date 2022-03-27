package com.swiggy.swiggyClone.dataModel;


import javax.persistence.*;

@Entity
@Table
public class PopularBrands {

    @Id
    @SequenceGenerator(name = "popular_brand_sequence", sequenceName = "popular_brand_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "popular_brand_sequence")
    private Long brandId;


    private Long restaurantId;
    private String restaurantName;
    private Double ratings;
    private String deliveryTime;

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public PopularBrands() {
    }

    public PopularBrands(Long brandId, Long restaurantId, String restaurantName, Double ratings, String deliveryTime) {
        this.brandId = brandId;
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.ratings = ratings;
        this.deliveryTime = deliveryTime;
    }

    public PopularBrands(Long restaurantId, String restaurantName, Double ratings, String deliveryTime) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.ratings = ratings;
        this.deliveryTime = deliveryTime;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Double getRatings() {
        return ratings;
    }

    public void setRatings(Double ratings) {
        this.ratings = ratings;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
}
