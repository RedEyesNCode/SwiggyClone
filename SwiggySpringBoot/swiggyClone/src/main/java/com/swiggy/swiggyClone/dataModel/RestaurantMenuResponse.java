package com.swiggy.swiggyClone.dataModel;

import java.util.List;

public class RestaurantMenuResponse extends StatusCodeModel{


    private List<PizzaMenuItemModel> pizzas;
    private List<DessertMenuItemModel> desserts;
    private List<MenuItemModel> recommended;
    private List<SnacksMenuItemModel> snacks;


    public RestaurantMenuResponse(String status, int code, String message, List<PizzaMenuItemModel> pizzas, List<DessertMenuItemModel> desserts, List<MenuItemModel> recommended, List<SnacksMenuItemModel> snacks) {
        super(status, code, message);
        this.pizzas = pizzas;
        this.desserts = desserts;
        this.recommended = recommended;
        this.snacks = snacks;
    }

    public RestaurantMenuResponse(String status, int code, List<PizzaMenuItemModel> pizzas, List<DessertMenuItemModel> desserts, List<MenuItemModel> recommended, List<SnacksMenuItemModel> snacks) {
        super(status, code);
        this.pizzas = pizzas;
        this.desserts = desserts;
        this.recommended = recommended;
        this.snacks = snacks;
    }

    public List<PizzaMenuItemModel> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<PizzaMenuItemModel> pizzas) {
        this.pizzas = pizzas;
    }

    public List<DessertMenuItemModel> getDesserts() {
        return desserts;
    }

    public void setDesserts(List<DessertMenuItemModel> desserts) {
        this.desserts = desserts;
    }

    public List<MenuItemModel> getRecommended() {
        return recommended;
    }

    public void setRecommended(List<MenuItemModel> recommended) {
        this.recommended = recommended;
    }

    public List<SnacksMenuItemModel> getSnacks() {
        return snacks;
    }

    public void setSnacks(List<SnacksMenuItemModel> snacks) {
        this.snacks = snacks;
    }
}
