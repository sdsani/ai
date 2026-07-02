package com.shahkaar.text_based_chat_loop.config;

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

import java.util.Scanner;

@Configuration
@Slf4j
public class AIConfig {

    @Bean
    ChatClient chatClient(ChatClient.Builder chatClientBuilder) {

        log.info("Creating ChatClient with SimpleLoggerAdvisor and MessageChatMemoryAdvisor");
        return chatClientBuilder.
                defaultAdvisors(new SimpleLoggerAdvisor(2)).
                build();
    }

    @Bean
    ApplicationRunner go(ChatClient chatClient) {
        return args -> {
            System.out.println("How can I help?\n");
            try (Scanner scanner = new Scanner(System.in)) {

                while (true) {
                    System.out.print("> ");
                    System.out.println("\n - " +
                            chatClient.
                                    prompt(scanner.nextLine()).
                                    advisors(spec -> spec.param(ChatMemory.CONVERSATION_ID, "DEMO")).
                                    call().content());
                }
            }
        };
    }

    @Bean
    ChatClientBuilderCustomizer chatMemoryCustomizer() {
        return builder -> {
            log.info("Adding MessageChatMemoryAdvisor with maxMessages=500 to ChatClient");
            builder.defaultAdvisors(
                    MessageChatMemoryAdvisor.builder(
                                    MessageWindowChatMemory.builder()
                                            .maxMessages(500)
                                            .build())
                            .build());
        };
    }
}
