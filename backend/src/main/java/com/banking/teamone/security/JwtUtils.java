package com.banking.teamone.security;

import com.banking.teamone.service.AdminDetailImpl;
import com.banking.teamone.service.CustomerIbDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;


@Component

public class JwtUtils {
    private static final Logger logger=LoggerFactory.getLogger(JwtUtils.class);
    @Value("${teamone.onlinebanking.jwtSecret}")
    private String jwtSecret;

    @Value("${teamone.onlinebanking.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication){

        CustomerIbDetailsImpl customerIbDetails= (CustomerIbDetailsImpl) authentication.getPrincipal();
        return Jwts.builder().setSubject(customerIbDetails.getUsername()).setIssuedAt(new Date()).
                setExpiration(new Date((new Date()).getTime()+jwtExpirationMs)).
                signWith(key(), SignatureAlgorithm.HS256).compact();
    }

    public String generateJwtTokenAdmin(Authentication authentication){
        AdminDetailImpl customerIbDetails= (AdminDetailImpl) authentication.getPrincipal();
        return Jwts.builder().setSubject(customerIbDetails.getUsername()).setIssuedAt(new Date()).
                setExpiration(new Date((new Date()).getTime()+jwtExpirationMs)).
                signWith(key(), SignatureAlgorithm.HS256).compact();
    }

    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String getUserNameFromJwt(String token){
//        logger.debug("Jwt token called in getusername"+" "+token);
        return Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken){

        try{
            System.out.println("Printing token in "+authToken);
           Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(authToken);
           return true;
        }catch(MalformedJwtException e){
            logger.error("Invalid Jwt token: {}", e.getMessage());
        }catch (ExpiredJwtException e){
            logger.error("JWT token in expired : {}", e.getMessage());

        }catch(UnsupportedJwtException e){
            logger.error("JWT token is unsupported: {}",e.getMessage());
        }catch(IllegalArgumentException e){
            logger.error("Jwt claim string is empty: {}",e.getMessage());
        }
        return false;

    }


}
