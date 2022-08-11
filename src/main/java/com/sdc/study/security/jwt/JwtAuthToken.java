package com.sdc.study.security.jwt;

import java.security.Key;
import java.util.Date;
import java.util.Optional;

import com.sdc.study.security.jwt.exception.JwtRuntimeException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.var;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtAuthToken implements AuthToken<Claims> {

    @Getter
    private final String token;
    private final Key key;

    private static final String AUTHORITIES_KEY = "role";

    JwtAuthToken(String token, Key key) {
        this.token = token;
        this.key = key;
    }

    JwtAuthToken(String id, String role, Date expiredDate, Key key) {
        this.key = key;
        this.token = createJwtAuthToken(id, role, expiredDate).get();
    }

    @Override
    public boolean validate() throws JwtRuntimeException{
        return getData() != null;
    }

    @Override
    public Claims getData() throws JwtRuntimeException{

        try {
        	return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        } catch (Exception e) {
        	return null;
        }
    }

    private Optional<String> createJwtAuthToken(String id, String role, Date expiredDate) {

        var token = Jwts.builder()
                .setSubject(id)
                .claim(AUTHORITIES_KEY, role)
                .signWith(key, SignatureAlgorithm.HS256)
                .setExpiration(expiredDate)
                .compact();

        return Optional.ofNullable(token);
    }
}
