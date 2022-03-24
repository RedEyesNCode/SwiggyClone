package com.swiggy.swiggyClone.dataModel;

public class UserWishlistResponse extends StatusCodeModel {


    private WishListModel data;


    public UserWishlistResponse(String status, int code, String message, WishListModel data) {
        super(status, code, message);
        this.data = data;
    }

    public WishListModel getData() {
        return data;
    }

    public void setData(WishListModel data) {
        this.data = data;
    }

    public UserWishlistResponse(String status, int code, WishListModel data) {
        super(status, code);
        this.data = data;
    }

    public UserWishlistResponse(String status, int code, String message) {
        super(status, code, message);
    }

    public UserWishlistResponse(String status, int code) {
        super(status, code);
    }
}
