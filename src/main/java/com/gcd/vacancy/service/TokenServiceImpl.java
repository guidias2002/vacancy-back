package com.gcd.vacancy.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenServiceImpl implements TokenService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Override
    public String generateToken(String login) {
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret);

        return JWT.create()
                .withIssuer("auth0")
                .withClaim("login", login)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 3600000)) // 1 hora
                .sign(algorithm);
    }
}
