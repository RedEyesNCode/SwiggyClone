package com.swiggy.swiggyClone.exceptionHandling;

import com.swiggy.swiggyClone.controllers.ApiController;
import com.swiggy.swiggyClone.dataModel.StatusCodeModel;
import org.springframework.beans.factory.annotation.Autowired;

public class CommonException extends RuntimeException{

    @Autowired
    private ApiController apiController;

    public CommonException() {

    }

    public CommonException(String message) {
        super(message);
    }

    public CommonException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommonException(Throwable cause) {
        super(cause);
    }

    public CommonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
