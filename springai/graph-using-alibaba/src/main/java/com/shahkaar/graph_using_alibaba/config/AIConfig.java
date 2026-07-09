package com.shahkaar.graph_using_alibaba.config;

import com.alibaba.cloud.ai.graph.CompiledGraph;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.ChatClientBuilderCustomizer;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Configuration
@Slf4j
public class AIConfig {

    @Bean
    ChatClient chatClient(ChatClient.Builder chatClientBuilder) {

        log.info("Creating ChatClient");
        return chatClientBuilder.
                build();
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
                            .build());
        };
    }

    @Bean
    ChatClientBuilderCustomizer loggingAdvisor() {
        return builder -> {
            log.info("Adding Logging advisor to ChatClientBuilderCustomizer");
            builder.defaultAdvisors(SimpleLoggerAdvisor.builder().order(2).build())
                            .build();
        };
    }

    @Bean
    ChatClientBuilderCustomizer conversationIDAdvisor() {

        return builder -> {
            log.info("Adding Conversation ID advisor to ChatClientBuilderCustomizer");
            builder.defaultAdvisors(spec -> spec.param(ChatMemory.CONVERSATION_ID, "DEMO"))
                    .build();
        };
    }

    @Bean
    ApplicationRunner go(CompiledGraph compiledGraph) {
        return _ -> {
            System.out.println("How can I help?\n");
            try (Scanner scanner = new Scanner(System.in)) {
                while (true) {
                    System.out.print("> ");
                    Map<String, Object> initialState = new HashMap<>();
                    initialState.put(
                            "user_question",
                            scanner.nextLine());
                    var workflowResult =
                            compiledGraph.invoke(initialState)
                                    .orElseThrow();
                    System.out.println(
                            "\n - "
                                    + workflowResult.data().get("response"));
                }
            }
        };
    }

}
