package com.swiggy.swiggyClone.filter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.swiggy.swiggyClone.controllers.UserContoller;
import com.swiggy.swiggyClone.dataModel.StatusCodeModel;
import com.swiggy.swiggyClone.exceptionHandling.JwtException;
import com.swiggy.swiggyClone.service.MyUserDetails;
import com.swiggy.swiggyClone.utils.JWTUtil;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{
	
	@Autowired
	MyUserDetails userDetailsService;

	@Autowired
	JWTUtil jwtUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException, SignatureException {
		final String authHeader = request.getHeader("Authorization");
		String username = null;
		String jwt = "";
		
		if(authHeader != null) {

			jwt = authHeader;

			try{
				username = jwtUtil.extractUserName(jwt);
			}catch (SignatureException e){
				response.addHeader("Content-Type","application/json");
				response.setContentType("application/json");
				Gson gson = new Gson();
				String json = gson.toJson(new StatusCodeModel("fail",403,"Invalid Token"));
				response.getWriter().print(json);
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
	//				e.printStackTrace();
				return;
			}


		}
		
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null ) {
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			if(jwtUtil.validateToken(jwt, userDetails)) {
				UsernamePasswordAuthenticationToken userPassAuthToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				userPassAuthToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(userPassAuthToken);
			}
		}
		filterChain.doFilter(request, response);
	}

}
