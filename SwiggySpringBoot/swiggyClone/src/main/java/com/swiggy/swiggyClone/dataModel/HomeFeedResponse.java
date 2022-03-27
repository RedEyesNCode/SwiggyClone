package com.swiggy.swiggyClone.dataModel;

import java.util.List;

public class HomeFeedResponse extends StatusCodeModel {



    private List<RestaurantsTable> restaurants;
    private List<GeneralOffers> offers;
    private List<PopularBrands> brands;
    private List<PopularCurations> curations;


    public List<PopularCurations> getCurations() {
        return curations;
    }

    public void setCurations(List<PopularCurations> curations) {
        this.curations = curations;
    }

    public HomeFeedResponse(String status, int code, String message, List<RestaurantsTable> restaurants, List<GeneralOffers> offers, List<PopularBrands> brands, List<PopularCurations> curations) {
        super(status, code, message);
        this.restaurants = restaurants;
        this.offers = offers;
        this.brands = brands;
        this.curations = curations;
    }

    public HomeFeedResponse(String status, int code, List<RestaurantsTable> restaurants, List<GeneralOffers> offers, List<PopularBrands> brands, List<PopularCurations> curations) {
        super(status, code);
        this.restaurants = restaurants;
        this.offers = offers;
        this.brands = brands;
        this.curations = curations;
    }

    public HomeFeedResponse(String status, int code, String message, List<RestaurantsTable> restaurants, List<GeneralOffers> offers, List<PopularBrands> brands) {
        super(status, code, message);
        this.restaurants = restaurants;
        this.offers = offers;
        this.brands = brands;
    }

    public HomeFeedResponse(String status, int code, List<RestaurantsTable> restaurants, List<GeneralOffers> offers, List<PopularBrands> brands) {
        super(status, code);
        this.restaurants = restaurants;
        this.offers = offers;
        this.brands = brands;
    }

    public List<RestaurantsTable> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<RestaurantsTable> restaurants) {
        this.restaurants = restaurants;
    }

    public List<GeneralOffers> getOffers() {
        return offers;
    }

    public void setOffers(List<GeneralOffers> offers) {
        this.offers = offers;
    }

    public List<PopularBrands> getBrands() {
        return brands;
    }

    public void setBrands(List<PopularBrands> brands) {
        this.brands = brands;
    }
}
