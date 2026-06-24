package com.shahkaar.springai_mcp.service;

import com.shahkaar.springai_mcp.model.dto.EventResponseDTO;
import io.modelcontextprotocol.client.McpSyncClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

// Brave starter: https://github.com/spring-projects/spring-ai-examples/tree/main/model-context-protocol/web-search/brave-starter
@Service
@Slf4j
public class BraveSearchService {

    private final ChatClient chatClient;

    private static final String SYSTEM_PROMPT = """
            "You are useful assistant and can perform web searches Brave's search API to reply to your questions.""";

    BraveSearchService(ChatClient.Builder builder,
                       List<McpSyncClient> mcpSyncClients) {

        chatClient = builder
                .defaultSystem( SYSTEM_PROMPT )
                .defaultTools(SyncMcpToolCallbackProvider.builder().mcpClients(mcpSyncClients).build())
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(MessageWindowChatMemory.builder().build()).build())
                .build();

        log.info("MCP clients count: {}", mcpSyncClients.size());
        log.info("Chat client has been built {}", "BraveSearchService");
    }

    public EventResponseDTO chat(String chatId, String prompt) {

//        String query = """
//                        Use the Brave Search tool to search patch.com for events in Eagan, Minnesota
//                        during July 2026.
//
//                        Return:
//                        - Event name
//                        - Date
//                        - Source URL
//                        - Title
//                        - Extra Snippet
//                        """;

        log.info("==> BraveSearchService.chat service call. chatID: {}, prompt: {}", chatId, prompt);

        // Setting tools under .tools() will make the tool available for this specific prompt.
        EventResponseDTO response = chatClient
                .prompt(prompt)
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
