package com.shahkaar.springai_mcp.config;

import com.shahkaar.springai_mcp.service.BraveSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.ChatClientBuilderCustomizer;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class AIConfig {

    @Bean
    ChatClient chatClient(ChatClient.Builder chatClientBuilder) {

        log.info("Creating ChatClient");
        return chatClientBuilder.
                defaultSystem(BraveSearchService.SYSTEM_PROMPT).
                build();
    }

    @Bean
    ChatClientBuilderCustomizer loggingAdvisor() {

        return builder -> {
            log.info("Adding Logging advisor to ChatClientBuilderCustomizer");
            builder.defaultAdvisors(SimpleLoggerAdvisor.
                            builder().
                            order(2).
                            build())
                    .build();
        };
    }

    @Bean
    ChatClientBuilderCustomizer chatMemoryCustomizer() {
        return builder -> {
            log.info("Adding MessageChatMemoryAdvisor with maxMessages=5 to ChatClientBuilderCustomizer");
            builder.defaultAdvisors(
                    MessageChatMemoryAdvisor.builder(
                                    MessageWindowChatMemory.builder()
                                            .maxMessages(5)
                                            .build())
                            .order(0)
                            .build());
        };
    }

    /*
    @Bean
    ChatClientBuilderCustomizer addQuestionTool(List<McpSyncClient> mcpSyncClients) {
        return builder -> {
            log.info("Adding mcpSyncClients to ChatClientBuilderCustomizer");
            builder
                    .defaultTools(
                            SyncMcpToolCallbackProvider.builder().mcpClients(mcpSyncClients).build());
        };
    }
     */

    @Bean
    ChatClientBuilderCustomizer addMcpTools(ToolCallbackProvider mcpToolCallbacks) {
        return builder ->
                builder.defaultTools(mcpToolCallbacks);
    }

}
