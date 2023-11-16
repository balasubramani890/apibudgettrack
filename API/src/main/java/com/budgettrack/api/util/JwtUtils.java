package com.budgettrack.api.util;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import com.budgettrack.api.Entity.UserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {
	
	
	private static String SECRET_KEY  = "BudgetTrack_SkBala16112023_Need512algorithmkeysoiusedthisaskeyforthealgorithm";
	
	// i just give 60 seconds only.  i.e the token validity is only 1 minite below;  15-10-2023 time extended to 1 hour
	private static long expiryDuration = 60 * 60;           
	
	public String generateJwt(UserEntity userEntity)
	{
		long milliTime = System.currentTimeMillis();
		long expiryTime = milliTime + expiryDuration * 1000;
		Date issuedAt = new Date(milliTime);
		Date expiryAt = new Date(expiryTime);
		
		Claims claims = Jwts
				.claims()
				.setIssuer(userEntity.getUserId().toString())
				.setIssuedAt(issuedAt)
				.setExpiration(expiryAt);
		
//		Optional claims put values
		claims.put("name", userEntity.getUserName());
		claims.put("type", userEntity.getUserType());
		
//		Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(SECRET_KEY), 
//                SignatureAlgorithm.HS512.getJcaName());
		
//		Key hmacKey = Keys.hmacShaKeyFor(Base64.getEncoder().encodeToString(SECRET_KEY.getBytes()).getBytes());
		Key hmacKey = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), SignatureAlgorithm.HS512.getJcaName());

		
//		SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
//		Key keyAlgorithm = Keys.secretKeyFor(SignatureAlgorithm.HS512);
		
		
		/*
		 * return Jwts.builder() .setClaims(claims) .signWith(key,
		 * SignatureAlgorithm.HS512) .compact();
		 */
		
		return Jwts.builder().setClaims(claims).signWith(hmacKey).compact();
	}
	
	
	 public Claims verify(String authorization) throws Exception {
		 
	        try {
	        	Key hmacKey = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), SignatureAlgorithm.HS512.getJcaName());
	            Claims claims = Jwts.parserBuilder().setSigningKey(hmacKey).build().parseClaimsJws(authorization).getBody();
	            System.out.println("Verify method: claims: " +claims);
	            return claims;
	        } catch(Exception e) {
	            throw new Exception("Access Denied");
	        }

	    }

}
