package com.swiggy.swiggyClone.dataModel;

import java.util.List;

public class AddressUserResponse extends StatusCodeModel {


    private List<AddressTable> addressTables;


    public AddressUserResponse(String status, int code, String message, List<AddressTable> addressTables) {
        super(status, code, message);
        this.addressTables = addressTables;
    }

    public AddressUserResponse(String status, int code, List<AddressTable> addressTables) {
        super(status, code);
        this.addressTables = addressTables;
    }

    public List<AddressTable> getAddressTables() {
        return addressTables;
    }

    public void setAddressTables(List<AddressTable> addressTables) {
        this.addressTables = addressTables;
    }
}
