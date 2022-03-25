package com.swiggy.swiggyClone.dataModel;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;


//Using the Json Ignore Properties beacuse we need to update this table upon insert in another table
@Entity
@Table
@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
public class WishListModel {

    @Id
    @SequenceGenerator(name = "wishlist_data_sequence", sequenceName = "wishlist_data_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wishlist_data_sequence")
    private Long wishlistId;




    private Long userId; // Will insert on this id and also filter by this id.
    private Long restaurantId;
    private boolean isAdded;
    private String restaurantName;
    private Double rating;
    private String deliveryTime;
    private Double discount;
    private boolean isSwiggyOne;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public WishListModel(Long wishlistId, Long userId, Long restaurantId, boolean isThereWishList, String restaurantName, Double rating, String deliveryTime, Double discount, boolean isSwiggyOne) {
        this.wishlistId = wishlistId;
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.isAdded = isThereWishList;
        this.restaurantName = restaurantName;
        this.rating = rating;
        this.deliveryTime = deliveryTime;
        this.discount = discount;
        this.isSwiggyOne = isSwiggyOne;
    }

    public WishListModel(Long userId, Long restaurantId, boolean isThereWishList, String restaurantName, Double rating, String deliveryTime, Double discount, boolean isSwiggyOne) {
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.isAdded = isThereWishList;
        this.restaurantName = restaurantName;
        this.rating = rating;
        this.deliveryTime = deliveryTime;
        this.discount = discount;
        this.isSwiggyOne = isSwiggyOne;
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

    public boolean isSwiggyOne() {
        return isSwiggyOne;
    }

    public void setSwiggyOne(boolean swiggyOne) {
        isSwiggyOne = swiggyOne;
    }

    public WishListModel(Long id, boolean isThereWishList) {
        this.wishlistId = id;
        this.isAdded = isThereWishList;
    }

    public WishListModel(boolean isThereWishList) {
        this.isAdded = isThereWishList;
    }

    public WishListModel() {
    }

    public Long getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(Long wishlistId) {
        this.wishlistId = wishlistId;
    }

    public boolean isAdded() {
        return isAdded;
    }

    public void setAdded(boolean added) {
        isAdded = added;
    }
}
