package com.swiggy.swiggyClone.dataModel;

public class PastOrderDetailResponse extends StatusCodeModel{
    private Allergens allergens;

    public PastOrderDetailResponse(String status, int code, String message, Allergens allergens) {
        super(status, code, message);
        this.allergens = allergens;
    }

    public PastOrderDetailResponse(String status, int code, Allergens allergens) {
        super(status, code);
        this.allergens = allergens;
    }
}
