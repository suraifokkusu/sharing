package com.example.sharing.controller;

import com.example.sharing.service.ZoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/zoom")
public class ZoomController {

    @Autowired
    private ZoomService zoomService;

    @PostMapping("/create-meeting")
    public ResponseEntity<String> createMeeting(@RequestHeader("Authorization") String accessToken,
                                                @RequestBody Map<String, Object> meetingDetails) {
        String topic = (String) meetingDetails.get("topic");
        String startTime = (String) meetingDetails.get("start_time");
        int duration = (int) meetingDetails.get("duration");

        String joinUrl = zoomService.createMeeting(topic, startTime, duration, accessToken);
        return ResponseEntity.ok(joinUrl);
    }
        @GetMapping("/authorize")
    public ResponseEntity<String> authorize() {
        String authorizationUrl = zoomService.getAuthorizationUrl();
        return ResponseEntity.ok(authorizationUrl);
    }

    @GetMapping("/callback")
    public ResponseEntity<String> callback(@RequestParam("code") String code) {
        ResponseEntity<String> accessTokenResponse = zoomService.getAccessToken(code);
        return accessTokenResponse;
    }
}
