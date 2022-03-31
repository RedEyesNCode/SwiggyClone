package com.swiggy.swiggyClone.dataModel;

import com.swiggy.swiggyClone.dataModel.commonProduct.AllProductTable;

import java.util.List;

public class AllProductsResponse  extends StatusCodeModel{

    private List<AllProductTable> data;

    public AllProductsResponse(String status, int code, String message, List<AllProductTable> data) {
        super(status, code, message);
        this.data = data;
    }

    public AllProductsResponse(String status, int code, List<AllProductTable> data) {
        super(status, code);
        this.data = data;
    }

    public List<AllProductTable> getData() {
        return data;
    }

    public void setData(List<AllProductTable> data) {
        this.data = data;
    }
}
