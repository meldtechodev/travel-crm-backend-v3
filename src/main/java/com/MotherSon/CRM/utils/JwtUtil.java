package com.MotherSon.CRM.utils;

import java.util.Base64;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.MotherSon.CRM.models.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	 private final String SECRET_KEY = Base64.getEncoder().encodeToString("mysecretkey_with_special_characters!".getBytes()); // Base64 encode the key
	    @SuppressWarnings("deprecation")
		public String generateToken(String email) {
	        return Jwts.builder()
	                .setSubject(email) // Set email as the subject
	                .setIssuedAt(new Date()) // Set token issue time
	                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Token valid for 10 hours
	                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // Use HS256 algorithm
	                .compact(); // Build the token
	    }
	    @SuppressWarnings("deprecation")
		public String extractUsername(String token) {
	        return Jwts.parser()
	                .setSigningKey(SECRET_KEY)
	                .parseClaimsJws(token)
	                .getBody()
	                .getSubject();
	    }

	    public boolean isTokenValid(String token, String username) {
	        return username.equals(extractUsername(token)) && !isTokenExpired(token);
	    }

	    @SuppressWarnings("deprecation")
		private boolean isTokenExpired(String token) {
	        return Jwts.parser()
	                .setSigningKey(SECRET_KEY)
	                .parseClaimsJws(token)
	                .getBody()
	                .getExpiration()
	                .before(new Date());
	    }
    

}
