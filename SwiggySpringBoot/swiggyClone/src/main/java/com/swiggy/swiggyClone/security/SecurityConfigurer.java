package com.swiggy.swiggyClone.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swiggy.swiggyClone.dataModel.StatusCodeModel;
import com.swiggy.swiggyClone.exceptionHandling.JwtAuthEntryPoint;
import com.swiggy.swiggyClone.filter.CustomAccessDeniedHandler;
import com.swiggy.swiggyClone.filter.JwtRequestFilter;
import com.swiggy.swiggyClone.service.MyUserDetails;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

	@Autowired
	private MyUserDetails myUserDetailsService;
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	@Autowired
	private JwtAuthEntryPoint unauthorizedHandler;


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailsService);
	}
	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		return (request, response, ex) -> {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);

			ServletOutputStream out = response.getOutputStream();
			new ObjectMapper().writeValue(out, new StatusCodeModel("fail",401,"Invalid Token"));
			out.flush();
		};
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests().antMatchers("/authJWT").permitAll()
			.anyRequest().authenticated()
			.and().sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).accessDeniedHandler(accessDeniedHandler());

		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();	
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
