package com.swiggy.swiggyClone.dataModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Table
@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
public class RestaurantDetailTable {


    @Id
    @SequenceGenerator(name = "restaurant_detail_sequence", sequenceName = "restaurant_detail_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restaurant_detail_sequence")
    private Long restaurantDetailId;


    private String RestaurantName;
    private String cuisines;
    private String location;
    private Double lat;
    private Double lng;
    private Double rating;
    private String time;
    private String price;


    public RestaurantDetailTable() {
    }

    public RestaurantDetailTable(Long restaurantDetailId, String restaurantName, String cuisines, String location, Double lat, Double lng, Double rating, String time, String price) {
        this.restaurantDetailId = restaurantDetailId;
        this.RestaurantName = restaurantName;
        this.cuisines = cuisines;
        this.location = location;
        this.lat = lat;
        this.lng = lng;
        this.rating = rating;
        this.time = time;
        this.price = price;
    }

    public RestaurantDetailTable(String restaurantName, String cuisines, String location, Double lat, Double lng, Double rating, String time, String price) {

        this.RestaurantName = restaurantName;
        this.cuisines = cuisines;
        this.location = location;
        this.lat = lat;
        this.lng = lng;
        this.rating = rating;
        this.time = time;
        this.price = price;
    }

    public Long getRestaurantDetailId() {
        return restaurantDetailId;
    }

    public void setRestaurantDetailId(Long restaurantDetailId) {
        this.restaurantDetailId = restaurantDetailId;
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
