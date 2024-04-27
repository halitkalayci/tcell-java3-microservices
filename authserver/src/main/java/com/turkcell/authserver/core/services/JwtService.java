package com.turkcell.authserver.core.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService
{
    @Value("${jwt.expiration}")
    private long EXPIRATION;
    @Value("${jwt.secret}")
    private String SECRET_KEY;

    public String generateToken(String username, Map<String, Object> extraClaims)
    {
        String token = Jwts
                .builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .claims(extraClaims)
                .signWith(getSigningKey())
                .compact();
        return token;
    }

    public Boolean validateToken(String token)
    {
        return getTokenClaims(token).getExpiration().after(new Date()); // Kendi ürettiğim token mı?
    }

    private Claims getTokenClaims(String token)
    {
        return Jwts
                .parser()
                .verifyWith((SecretKey) getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private Key getSigningKey()
    {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
