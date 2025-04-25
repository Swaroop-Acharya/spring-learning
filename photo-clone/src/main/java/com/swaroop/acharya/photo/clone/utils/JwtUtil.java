package com.swaroop.acharya.photo.clone.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
@Slf4j
public class JwtUtil {

    @Value("${spring.workflow.jwt.secrete}")
    private String secreteKey;

    public SecretKey getSecreteKey(){
        return Keys.hmacShaKeyFor(this.secreteKey.getBytes(StandardCharsets.UTF_8));
    }


    public String generateToken(String userId){
        long nowMillis= System.currentTimeMillis();
        long expMillis=nowMillis+1000*60*60;
        Date exp = new Date(expMillis);

        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date(nowMillis))
                .setExpiration(exp)
                .signWith(getSecreteKey(),SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validate(String token){
        try{
            String[] parts = token.split("\\.");
            if(parts.length!=3){
                log.error("Invalid token");
                return false;
            }
            Jwts.parserBuilder()
                    .setSigningKey(getSecreteKey())
                    .build()
                    .parseClaimsJws(token);

            return true;
        }catch (SignatureException e){
            log.error("Signature token is invalid",e);
            return false;
        }catch (Exception e){
            log.error("Something went wrong",e);
            return false;
        }
    }

    public String extractSubject(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSecreteKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }



}
