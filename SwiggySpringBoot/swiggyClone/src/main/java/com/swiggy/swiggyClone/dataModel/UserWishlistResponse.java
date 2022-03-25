package com.swiggy.swiggyClone.dataModel;

import java.util.List;

public class UserWishlistResponse extends StatusCodeModel {


    private List<WishListModel> data;


    public UserWishlistResponse(String status, int code, String message, List<WishListModel> data) {
        super(status, code, message);
        this.data = data;
    }

    public List<WishListModel> getData() {
        return data;
    }

    public void setData(List<WishListModel> data) {
        this.data = data;
    }

    public UserWishlistResponse(String status, int code, List<WishListModel> data) {
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
