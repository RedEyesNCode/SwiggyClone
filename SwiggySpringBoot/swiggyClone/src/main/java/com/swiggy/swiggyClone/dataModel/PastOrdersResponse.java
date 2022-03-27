package com.swiggy.swiggyClone.dataModel;

import java.util.List;

public class PastOrdersResponse extends StatusCodeModel{

    private List<PastOrders> pastOrders;

    public PastOrdersResponse(String status, int code, String message, List<PastOrders> pastOrders) {
        super(status, code, message);
        this.pastOrders = pastOrders;
    }

    public PastOrdersResponse(String status, int code, List<PastOrders> pastOrders) {
        super(status, code);
        this.pastOrders = pastOrders;
    }

    public List<PastOrders> getPastOrders() {
        return pastOrders;
    }

    public void setPastOrders(List<PastOrders> pastOrders) {
        this.pastOrders = pastOrders;
    }
}
