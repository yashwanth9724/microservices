package com.microservices.microservices.controller;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final JwtEncoder jwtEncoder;

    public AuthController(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    @PostMapping("/token")
    public Map<String, String> token(@RequestParam(defaultValue = "user1") String username) {

        Instant now = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("local-auth")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(3600))
                .subject(username)
                .claim("roles", List.of("PAYMENT_WRITE", "PAYMENT_READ"))
                .build();

        // ? IMPORTANT: force HS256 so Nimbus selects the right key
        JwsHeader headers = JwsHeader.with(MacAlgorithm.HS256).build();

        String token = jwtEncoder.encode(JwtEncoderParameters.from(headers, claims)).getTokenValue();

        return Map.of("access_token", token);
    }
}
