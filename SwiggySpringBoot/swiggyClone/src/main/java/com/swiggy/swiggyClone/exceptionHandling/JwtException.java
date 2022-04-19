package com.swiggy.swiggyClone.exceptionHandling;


import io.jsonwebtoken.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class JwtException extends SignatureException {


    public JwtException(String message) {
        super(message);
    }
}
