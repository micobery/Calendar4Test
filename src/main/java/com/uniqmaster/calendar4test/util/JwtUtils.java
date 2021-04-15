package com.uniqmaster.calendar4test.util;

import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.Date;

import io.jsonwebtoken.Jwts;


@Service
@PropertySource("classpath:/config.properties")
public class JwtUtils {

    public long JWT_TOKEN_VALIDITY = 60 * 60 * 1000;

    @Value("${jwt.secret}")
    private String SECRET;

    public String generateToken(String username) {

        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + (JWT_TOKEN_VALIDITY)))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody().getSubject();
    }
}
