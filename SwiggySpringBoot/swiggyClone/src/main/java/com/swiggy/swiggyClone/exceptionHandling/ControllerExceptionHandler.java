package com.swiggy.swiggyClone.exceptionHandling;


import com.swiggy.swiggyClone.dataModel.StatusCodeModel;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<StatusCodeModel> resourceNotFoundException(ForbiddenException ex, WebRequest request) {
     /*   ErrorMessage message = new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
*/
        StatusCodeModel statusCodeModel = new StatusCodeModel("fail",404,ex.getMessage());
        return new ResponseEntity<StatusCodeModel>(statusCodeModel,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<StatusCodeModel> invalidToken() {
        StatusCodeModel statusCodeModel = new StatusCodeModel("fail",404,"Invalid Token");
        return new ResponseEntity<StatusCodeModel>(statusCodeModel,HttpStatus.NOT_FOUND);
    }
    /*@ExceptionHandler(value= {NullPointerException.class, JwtException.class})
    public ResponseEntity<Object> handleSpecificExceptions(Exception e, WebRequest req){
        String errorMessageDescription = e.getLocalizedMessage();
        if(errorMessageDescription == null) {
            errorMessageDescription = e.toString().concat(" This is from Specific Exception method handler");
        }
        ErrorMessage errorMessage = new ErrorMessage(403,new Date(), errorMessageDescription,"iNVALID tOKEN");
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.FORBIDDEN);
    }*/


}
