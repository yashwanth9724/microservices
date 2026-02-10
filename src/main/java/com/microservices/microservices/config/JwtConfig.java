package com.microservices.microservices.config;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.*;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import org.springframework.security.crypto.keygen.KeyGenerators;

import javax.crypto.spec.SecretKeySpec;

@Configuration
public class JwtConfig {

    @Bean
    public JwtEncoder jwtEncoder(@Value("${app.jwt.secret}") String secret) {
        return new NimbusJwtEncoder(new ImmutableSecret<>(secret.getBytes(java.nio.charset.StandardCharsets.UTF_8)));
    }


    @Bean
    public JwtDecoder jwtDecoder(@Value("${app.jwt.secret}") String secret) {

        SecretKey key = new SecretKeySpec(secret.getBytes(java.nio.charset.StandardCharsets.UTF_8), "HmacSHA256");

        return NimbusJwtDecoder.withSecretKey(key).build();
    }
}
