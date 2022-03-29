package com.swiggy.swiggyClone.controllers;

import com.swiggy.swiggyClone.AuthenticationRequest;
import com.swiggy.swiggyClone.AuthenticationResponse;
import com.swiggy.swiggyClone.dataModel.SignupModel;
import com.swiggy.swiggyClone.dataModel.StatusCodeModel;
import com.swiggy.swiggyClone.service.ApiService;
import com.swiggy.swiggyClone.service.MyUserDetails;
import com.swiggy.swiggyClone.utils.JWTUtil;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserContoller {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetails userDetailsService;

	@Autowired
	private ApiService apiService;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@RequestMapping("/hello")
	public ResponseEntity<String> getHello(){
		return ResponseEntity.ok("Hello");
	}
	
	/*@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public ResponseEntity<?> createJWT(@RequestBody AuthenticationRequest authRequest) throws Exception{
		try {
			SignupModel signupModel = apiService.loginUser(authRequest.getUsername(),authRequest.getPassword());
			if(signupModel!=null){

				authenticationManager
						.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
			}else {
				return ResponseEntity.badRequest().body(new StatusCodeModel("fail", 400, "Incorrect User Name and Password"));
			}
		}catch(BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
		final String jwt = jwtUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
*/
	@RequestMapping(value = "/authJWT", method = RequestMethod.POST)
	public ResponseEntity<?> createJWTFinal(@RequestBody AuthenticationRequest authRequest) throws Exception{
		try {
			ResponseEntity<?> responseEntity = apiService.loginUserJWT(authRequest.getUsername(), authRequest.getPassword());
			if(responseEntity.getStatusCodeValue()==200){


				authenticationManager
						.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
			}else {
				return ResponseEntity.badRequest().body(new StatusCodeModel("fail", 400, "Incorrect User Name and Password"));
			}
		}catch(BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
		final String jwt = jwtUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse("success",200,"Login SuccessFullly",jwt,apiService.loginUser(authRequest.getUsername(), authRequest.getPassword())));
	}

	@RequestMapping("/403")
	public String accessDenied() {
		return "errors/403";
	}

	@ExceptionHandler(SignatureException.class)
	public StatusCodeModel HandlerException(){
		return new StatusCodeModel("fail",400,"Invalid TOken Signature");


	}
}
