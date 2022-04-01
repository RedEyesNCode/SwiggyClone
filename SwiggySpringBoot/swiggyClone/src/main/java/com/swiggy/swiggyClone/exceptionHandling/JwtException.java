package com.swiggy.swiggyClone.exceptionHandling;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class JwtException extends ForbiddenException {

    public JwtException(String message) {
        super(message);
    }
}
