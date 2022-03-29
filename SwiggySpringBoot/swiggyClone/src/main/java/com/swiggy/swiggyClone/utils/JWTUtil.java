package com.swiggy.swiggyClone.utils;

import com.swiggy.swiggyClone.dataModel.StatusCodeModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTUtil {

	private String SECRET_KEY = "AllThisFor4Hearts";
	
	public ResponseEntity<?> extractUserName(String token) {
		try{
			return ResponseEntity.ok(Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject());
		}catch (SignatureException e){
			return ResponseEntity.badRequest().body(new StatusCodeModel("fail",403,"Invalid Token"));
		}

	}

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 10 * 60 * 60 * 1000))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
		Date expiryDate = claims.getExpiration();
		String username = claims.getSubject();
		if (userDetails.getUsername().equals(username) && (new Date().before(expiryDate))) {
			return true;
		}
		return false;
	}

}
