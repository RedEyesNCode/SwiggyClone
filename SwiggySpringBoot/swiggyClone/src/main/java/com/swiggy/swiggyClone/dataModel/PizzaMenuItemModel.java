package com.swiggy.swiggyClone.dataModel;


import javax.persistence.*;

@Entity
@Table
public class PizzaMenuItemModel {


    @Id
    @SequenceGenerator(name = "pizza_menu_sequence", sequenceName = "pizza_menu_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pizza_menu_sequence")
    private Long menuId;


    private String dishName;
    private boolean isVeg;
    private Double price;
    private String description;

    public PizzaMenuItemModel() {
    }

    public PizzaMenuItemModel(Long menuId, String dishName, boolean isVeg, Double price, String description) {
        this.menuId = menuId;
        this.dishName = dishName;
        this.isVeg = isVeg;
        this.price = price;
        this.description = description;
    }

    public PizzaMenuItemModel(String dishName, boolean isVeg, Double price, String description) {
        this.dishName = dishName;
        this.isVeg = isVeg;
        this.price = price;
        this.description = description;
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
}
