package com.example.sharing.controller;

import com.google.auth.oauth2.TokenVerifier;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.json.webtoken.JsonWebSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
public class AuthController {

    private static final JacksonFactory JACKSON_FACTORY = JacksonFactory.getDefaultInstance();

    @Value("${  security:\n" +
            "    oauth2:\n" +
            "      client:\n" +
            "        registration:\n" +
            "          google:\n" +
            "            client-id:}")
    private String clientId;

    @PostMapping("/api/auth/google")
    public Map<String, Object> verifyToken(@RequestBody Map<String, String> payload) {
        String token = payload.get("token");

        try {
            TokenVerifier tokenVerifier = TokenVerifier.newBuilder()
                    .setAudience(clientId)
                    .build();

            JsonWebSignature jsonWebSignature = JsonWebSignature.parser(JACKSON_FACTORY)
                    .setPayloadClass(JsonWebSignature.Payload.class)
                    .parse(token);

            JsonWebSignature.Payload jwtPayload = jsonWebSignature.getPayload();
            String userId = jwtPayload.getSubject();
            String email = (String) jwtPayload.get("email");
            String name = (String) jwtPayload.get("name");

            // Дополнительная обработка данных пользователя
            return Map.of(
                    "userId", userId,
                    "email", email,
                    "name", name
            );
        } catch (IOException e) {
            e.printStackTrace();
            return Map.of("error", "Token verification failed: " + e.getMessage());
        }
    }
}
