package com.shahkaar.springai_mcp.service;

import com.shahkaar.springai_mcp.model.dto.EventResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

// Brave starter: https://github.com/spring-projects/spring-ai-examples/tree/main/model-context-protocol/web-search/brave-starter
@Service
@Slf4j
@RequiredArgsConstructor
public class BraveSearchService {

    private final ChatClient chatClient;

    public static final String SYSTEM_PROMPT = """
            "You are useful assistant and can perform web searches Brave's search API to reply to your questions.""";

    public EventResponseDTO chat(String chatId, String prompt) {

        log.info("==> BraveSearchService.chat service call. chatID: {}, prompt: {}", chatId, prompt);

        // Tools (McpSyncClient), advisors (default) and system prompt are configured in the ChatClient bean in AIConfig.java
        EventResponseDTO response = chatClient
                .prompt(prompt)
                //.system(SYSTEM_PROMPT)
                //.tools(SyncMcpToolCallbackProvider.builder().mcpClients(mcpSyncClients).build())  // Added to the tools
                .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, chatId))
                .call()
                .entity(EventResponseDTO.class);      // Asking to transform response into a Java object

        Assert.notNull(response, "Response is null");
        log.info(response.toString());
        log.info("Events count: {}", response.getEvents().size());
        return response;
    }

}
