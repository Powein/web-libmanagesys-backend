package com.powei.testmybatis.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.HashMap;

public class JwtUtils {
    private static String key = "Powei";

    public static String generateJWT(HashMap<String, Object> claims) {
        return Jwts.builder()
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS256, key)
                .setExpiration(new java.util.Date(System.currentTimeMillis() + 3600 * 1000))
                .compact();
    }

    public static Claims parseJWT(String jwtStr) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwtStr)
                .getBody();
    }
}
