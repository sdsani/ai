package com.shahkaar.springai_mcp.controller;

import com.shahkaar.springai_mcp.model.dto.EventResponseDTO;
import com.shahkaar.springai_mcp.service.BraveSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ai")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin
public class AIController {

    private final BraveSearchService braveSearchService;

    // Query examples
    // curl "https://api.search.brave.com/res/v1/web/search?q=artificial+intelligence" -H "X-Subscription-Token: key-here"
    // curl -s --compressed \
//      "https://api.search.brave.com/res/v1/web/search?q=site:patch.com+Eagan+MN+events+July+2026&count=20" \
//      -H "Accept: application/json" \
//      -H "X-Subscription-Token: key-here" | jq
    // http://localhost:8081/ai/bravesearch
    @PostMapping("/bravesearch")
    public EventResponseDTO braveSearch(@RequestBody Map<String, String> request) {

        String prompt = request.get("prompt");
        log.info("====> Calling ai/mcp, Prompt: {}", prompt);
        return braveSearchService.chat("1234", prompt);
    }
}
