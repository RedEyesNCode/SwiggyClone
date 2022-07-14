package com.swiggy.swiggyClone.dataModel.offers;

public class ApplyPromoBody {

    private int orderId;
    private int offerId;
    private boolean isApplying;

    public ApplyPromoBody() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    public boolean isApplying() {
        return isApplying;
    }

    public void setApplying(boolean applying) {
        isApplying = applying;
    }

    public ApplyPromoBody(int orderId, int offerId, boolean isApplying) {
        this.orderId = orderId;
        this.offerId = offerId;
        this.isApplying = isApplying;
    }
}
