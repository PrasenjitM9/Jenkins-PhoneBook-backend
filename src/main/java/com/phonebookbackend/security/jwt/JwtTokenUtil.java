package com.phonebookbackend.security.jwt;

import com.phonebookbackend.security.services.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import java.util.Date;
import org.slf4j.Logger;

/*Generate and resolve JWT token*/
@Component
public class JwtTokenUtil {
 private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);
 private static final String CLAIM_KEY_USERNAME = "sub";
 private static final String CLAIM_KEY_CREATED = "created";
 @Value("${phonebookbackend.app.secret}")
    private String secret;
 @Value("${phonebookbackend.app.expiration}")
    private Long expiration;

 /*
 * Generate token of JWT base on response
 * */
 public String generateJwtToken(Authentication authentication){
     UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
     return Jwts.builder()
             .setSubject(userPrincipal.getUsername())
             .setIssuedAt(new Date())
             .setExpiration(generateExpirationDate())
             .signWith(SignatureAlgorithm.HS512, secret)
             .compact();
 }

 /*
 * get payload from JWT of token
 * */

 private Claims getClaimsFromToken(String token){
     Claims claims = null;
     try {
         claims = Jwts.parser()
                 .setSigningKey(secret)
                 .parseClaimsJws(token)
                 .getBody();
     }catch (Exception e){
         LOGGER.info("JWT format authorization failed:{}"+token);
     }
        return claims;
 }


    private Date generateExpirationDate() {
     return new Date(System.currentTimeMillis() + expiration *1000);
    }

/*
* get logged user name from token
*
* */
    public String getUserNameFromToken(String token){
     String username;
     try {
         Claims claims = getClaimsFromToken(token);
         username = claims.getSubject();
     }catch (Exception e){
         username = null;
     }
     return username;
    }

    /*
    * Validate if the token still valid
    * */
    public boolean validateToken(String authToken){
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            LOGGER.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            LOGGER.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            LOGGER.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            LOGGER.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            LOGGER.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

    private boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

}
