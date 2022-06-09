package com.swiggy.swiggyClone.dataModel.adminModels;

public class RestaurantBody {


    private String RestaurantName;
    private String cuisines;
    private String location;
    private Double lat;
    private Double lng;
    private Double rating;
    private String time;
    private String price;

    private String deliveryTime;
    private Double discount;
    private boolean isSwiggyOne;

    private String restaurantImage;

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

    public String getRestaurantImage() {
        return restaurantImage;
    }

    public void setRestaurantImage(String restaurantImage) {
        this.restaurantImage = restaurantImage;
    }

    public RestaurantBody(String restaurantName, String cuisines, String location, Double lat, Double lng, Double rating, String time, String price, String deliveryTime, Double discount, boolean isSwiggyOne, String restaurantImage) {
        RestaurantName = restaurantName;
        this.cuisines = cuisines;
        this.location = location;
        this.lat = lat;
        this.lng = lng;
        this.rating = rating;
        this.time = time;
        this.price = price;
        this.deliveryTime = deliveryTime;
        this.discount = discount;
        this.isSwiggyOne = isSwiggyOne;
        this.restaurantImage = restaurantImage;
    }

    public RestaurantBody() {
    }

    public String getRestaurantName() {
        return RestaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        RestaurantName = restaurantName;
    }

    public String getCuisines() {
        return cuisines;
    }

    public void setCuisines(String cuisines) {
        this.cuisines = cuisines;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
