package com.example.sharing.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Controller
public class ProxyController {

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String clientSecret;

    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String redirectUri;

    @GetMapping("/oauth2/authorization/google")
    public ResponseEntity<Void> redirectToGoogle(@RequestParam String state) {
        String googleAuthorizationUrl = UriComponentsBuilder.fromUriString("https://accounts.google.com/o/oauth2/auth")
                .queryParam("response_type", "code")
                .queryParam("client_id", clientId)
                .queryParam("scope", "profile email")
                .queryParam("state", state)
                .queryParam("redirect_uri", redirectUri)
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(googleAuthorizationUrl));

        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }
}
