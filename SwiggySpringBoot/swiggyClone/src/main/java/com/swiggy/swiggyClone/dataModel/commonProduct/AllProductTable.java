package com.swiggy.swiggyClone.dataModel.commonProduct;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table
@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
public class AllProductTable {

    @Id
    @SequenceGenerator(name = "common_products_sequence", sequenceName = "common_products_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "common_products_sequence")
    private Long menuId;


    private String dishName;
    private boolean isVeg;
    private Double price;
    private String description;
    private String productType;

    public AllProductTable(String dishName, boolean isVeg, Double price, String description, String productType) {
        this.dishName = dishName;
        this.isVeg = isVeg;
        this.price = price;
        this.description = description;
        this.productType = productType;
    }

    public AllProductTable(Long menuId, String dishName, boolean isVeg, Double price, String description, String productType) {
        this.menuId = menuId;
        this.dishName = dishName;
        this.isVeg = isVeg;
        this.price = price;
        this.description = description;
        this.productType = productType;
    }

    public AllProductTable() {
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
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
}
