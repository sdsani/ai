package com.shahkaar.controller;

import com.shahkaar.service.AdkChatService;
import com.shahkaar.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/chat")
@Slf4j
public class AdkChatController {

    private final AdkChatService chatService;

    public AdkChatController(AdkChatService chatService) {
        this.chatService = chatService;
    }

    // curl -X POST "http://localhost:8080/api/chat/sessions"
    @PostMapping("/sessions")
    public Map<String, String> createSession() {

        log.info("{} Creating a new chat session", Constants.LOG_PREFIX);
        String sessionId = chatService.createSession();
        return Map.of(
                "sessionId", sessionId,
                "message", "Session created"
        );
    }

    // curl -X POST "http://localhost:8080/api/chat/sessions/5e9779f5-3887-41f3-9428-0027ca0192b9/Capital%20of%20France"
    @PostMapping("/sessions/{sessionId}/messages")
    public Map<String, String> sendMessage(
            @PathVariable String sessionId,
            @RequestBody MessageRequest request) {

        log.info("{} Sending message to session {}: {}", Constants.LOG_PREFIX, sessionId, request.message());
        String response = chatService.sendMessage(
                sessionId,
                request.message()
        );

        return Map.of("response", response);
    }

    public record MessageRequest(String message) {}
}
