package com.swiggy.swiggyClone.dataModel.adminModels;

public class FoodItemBody {

    private String dishName;
    private boolean isVeg;
    private Double price;
    private String description;
    private String productType;

    private String productImage;

    public FoodItemBody(String dishName, boolean isVeg, Double price, String description, String productType, String productImage) {
        this.dishName = dishName;
        this.isVeg = isVeg;
        this.price = price;
        this.description = description;
        this.productType = productType;
        this.productImage = productImage;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public boolean isVeg() {
        return isVeg;
    }

    public void setVeg(boolean veg) {
        isVeg = veg;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }
}
