package com.swiggy.swiggyClone.dataModel;

import java.util.List;

public class GenieResponse  extends StatusCodeModel{


    private List<SwiggiGenieTable> data;

    public GenieResponse(String status, int code, String message, List<SwiggiGenieTable> data) {
        super(status, code, message);
        this.data = data;
    }

    public GenieResponse(String status, int code, List<SwiggiGenieTable> data) {
        super(status, code);
        this.data = data;
    }
}
