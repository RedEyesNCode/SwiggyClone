package com.swiggy.swiggyClone.dataModel.cart;

public class OrderInsertBody {


    private Long userId;
        private Long restaurantId;
        private Long productId;
        private String type;

        private String paymentType;



    public OrderInsertBody(Long userId, Long restaurantId, Long productId, String type) {
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.productId = productId;
        this.type = type;
    }

    public OrderInsertBody() {
    }

    public OrderInsertBody(Long userId, Long restaurantId, Long productId) {
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.productId = productId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
