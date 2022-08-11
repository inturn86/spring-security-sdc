package com.sdc.study.security.jwt;

import java.util.Date;

import org.springframework.security.core.Authentication;

public interface AuthTokenProvider<T> {
    T createAuthToken(String id, String role, Date expiredDate);
    T convertAuthToken(String token);
	Authentication getAuthentication(JwtAuthToken authToken);
}