package com.swiggy.swiggyClone.exceptionHandling;

import com.swiggy.swiggyClone.dataModel.StatusCodeModel;

public class ForbiddenDataClass extends StatusCodeModel {
    public ForbiddenDataClass(String status, int code, String message) {
        super(status, code, message);
    }

    public ForbiddenDataClass(String status, int code) {
        super(status, code);
    }
}
