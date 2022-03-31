package com.swiggy.swiggyClone.dataModel;

import javax.persistence.*;


@Entity
@Table
public class DessertMenuItemModel {

    @Id
    @SequenceGenerator(name = "desert_menu_sequence", sequenceName = "desert_menu_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "desert_menu_sequence")
    private Long desertMenuId;

    public DessertMenuItemModel() {
    }

    private String dishName;
    private boolean isVeg;
    private Double price;
    private String description;

    public DessertMenuItemModel(Long menuId, String dishName, boolean isVeg, Double price, String description) {
        this.desertMenuId = menuId;
        this.dishName = dishName;
        this.isVeg = isVeg;
        this.price = price;
        this.description = description;
    }

    public DessertMenuItemModel(String dishName, boolean isVeg, Double price, String description) {
        this.dishName = dishName;
        this.isVeg = isVeg;
        this.price = price;
        this.description = description;
    }

    public Long getDesertMenuId() {
        return desertMenuId;
    }

    public void setDesertMenuId(Long desertMenuId) {
        this.desertMenuId = desertMenuId;
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
}
