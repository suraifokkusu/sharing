package com.example.sharing.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Service
public class ZoomService {

    private static final Logger logger = LogManager.getLogger(ZoomService.class);

    @Value("${zoom.api.client-id}")
    private String clientId;

    @Value("${zoom.api.client-secret}")
    private String clientSecret;

    @Value("${zoom.api.redirect-uri}")
    private String redirectUri;

    @Value("${zoom.oauth2.user-info-uri}")
    private String usersInfoUri;

    @Value("${zoom.oauth2.token-uri}")
    private String tokenUri;

    private final RestTemplate restTemplate = new RestTemplate();

    public String createMeeting(String topic, String startTime, int duration, String accessToken) {
        logger.debug("Entering createMeeting method...");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.set("Content-Type", "application/json");

        Map<String, Object> request = new HashMap<>();
        request.put("topic", topic);
        request.put("type", 2); // Scheduled meeting
        request.put("start_time", startTime);
        request.put("duration", duration);
        request.put("timezone", "UTC");

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);

        logger.debug("Sending request to Zoom API with headers: {}", headers);
        logger.debug("Request body: {}", request);

        ResponseEntity<String> response;
        try {
            response = restTemplate.exchange(usersInfoUri + "/meetings", HttpMethod.POST, entity, String.class);
            logger.info("Response from Zoom API: {}", response.getBody());
        } catch (Exception e) {
            logger.error("Error occurred while sending request to Zoom API: {}", e.getMessage());
            throw new RuntimeException("Failed to create meeting", e);
        }

        String responseBody = response.getBody();
        if (responseBody != null) {
            logger.debug("Parsing response body...");
            Map<String, Object> responseMap = new HashMap<>();
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                responseMap = objectMapper.readValue(responseBody, new TypeReference<Map<String, Object>>() {});
            } catch (JsonProcessingException e) {
                logger.error("Failed to parse response body: {}", e.getMessage());
            }

            if (responseMap.containsKey("join_url")) {
                return responseMap.get("join_url").toString();
            } else {
                throw new RuntimeException("Failed to create meeting: join_url not found in response");
            }
        } else {
            throw new RuntimeException("Failed to create meeting: No response from Zoom");
        }
    }

        public String getAuthorizationUrl() {
        return UriComponentsBuilder.fromHttpUrl("https://zoom.us/oauth/authorize")
                .queryParam("response_type", "code")
                .queryParam("client_id", clientId)
                .queryParam("redirect_uri", redirectUri)
                .queryParam("scope=meeting:write meeting:write:admin")
                .toUriString();
    }

    public ResponseEntity<String> getAccessToken(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        Map<String, String> params = new HashMap<>();
        params.put("grant_type", "authorization_code");
        params.put("code", code);
        params.put("redirect_uri", redirectUri);
        params.put("client_id", clientId);
        params.put("client_secret", clientSecret);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(params, headers);
        return restTemplate.postForEntity(tokenUri, request, String.class);
    }
}