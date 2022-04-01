package com.swiggy.swiggyClone.exceptionHandling;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swiggy.swiggyClone.controllers.ApiController;
import com.swiggy.swiggyClone.dataModel.StatusCodeModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthEntryPoint.class);

    @Autowired
    private ApiController apiController;


    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {
        logger.error("Responding with unauthorized error. Message - {}", e.getMessage());



        ServletOutputStream out = httpServletResponse.getOutputStream();
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        new ObjectMapper().writeValue(out, new StatusCodeModel("fail",401,"Invalid Token"));
        out.flush();
    }
}
