package com.swiggy.swiggyClone.dataModel.offers;

import com.swiggy.swiggyClone.dataModel.StatusCodeModel;

import java.util.List;

public class OffersResponse extends StatusCodeModel {

    private List<OfferTable> offerTables;

    public OffersResponse(String status, int code, String message, List<OfferTable> offerTables) {
        super(status, code, message);
        this.offerTables = offerTables;
    }

    public List<OfferTable> getOfferTables() {
        return offerTables;
    }

    public void setOfferTables(List<OfferTable> offerTables) {
        this.offerTables = offerTables;
    }

    public OffersResponse(String status, int code, List<OfferTable> offerTables) {
        super(status, code);
        this.offerTables = offerTables;
    }

    public OffersResponse(String status, int code, String message) {
        super(status, code, message);
    }

    public OffersResponse(String status, int code) {
        super(status, code);
    }
}
