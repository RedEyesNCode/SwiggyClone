package com.swiggy.swiggyClone.filter;

import com.swiggy.swiggyClone.service.MyUserDetails;
import com.swiggy.swiggyClone.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

@Component
public class JwtRequestFilter extends OncePerRequestFilter{
	
	@Autowired
	MyUserDetails userDetailsService;

	@Autowired
	JWTUtil jwtUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String authHeader = request.getHeader("Authorization");
		String username = null;
		String jwt = "";
		
		if(authHeader != null) {
			jwt = authHeader;
			ResponseEntity<?> responseEntity = jwtUtil.extractUserName(jwt);
			if(responseEntity.getStatusCodeValue()==200){
				username = (String) responseEntity.getBody();
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
