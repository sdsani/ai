package com.shahkaar.assitant_agent.controller;

import com.shahkaar.assitant_agent.model.dto.ChatRequest;
import com.shahkaar.assitant_agent.model.dto.CommandResponse;
import com.shahkaar.assitant_agent.service.ChatService;
import com.shahkaar.assitant_agent.service.RAGSearchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ai")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin
public class AIController {

    private final RAGSearchService searchService;
    private final ChatService chatService;

    // http://localhost:8081/ai/ping
    @GetMapping("/ping")
    public String ping() {
        return "Pong! DevOpsGPT is running.";
    }

    // http://localhost:8081/ai/searchrag
    @GetMapping("/searchrag")
    public String ragSearch() {

        // Following queries work
        //String query = "AWS S3 Basics";
        //String query = "aws-s3-basics";
        //String query = "AWS S3 Knowledge base";

        String query = "AWS CLI Getting Started";
        log.info("====> Calling Search RAG, query: {}", query);

        return searchService.searchRelevantDocumentsAsString(query);
    }

    // http://localhost:8081/ai/devopsgpt
    @PostMapping("/devopsgpt")
    public CommandResponse devOpsGPT(@Valid @RequestBody ChatRequest chatRequest) {

        log.info("====> Calling ai/devopsgpt, ChatRequest: {}, {}", chatRequest, chatRequest.getClass().getName());
        return chatService.getRagReply(chatRequest.prompt());
    }

}
