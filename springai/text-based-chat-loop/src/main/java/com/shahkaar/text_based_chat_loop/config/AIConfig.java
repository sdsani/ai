package com.shahkaar.text_based_chat_loop.config;

import lombok.extern.slf4j.Slf4j;
import org.springaicommunity.agent.tools.AskUserQuestionTool;
import org.springaicommunity.agent.utils.CommandLineQuestionHandler;
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

        log.info("Creating ChatClient with SimpleLoggerAdvisor and ChatClientBuilderCustomizer");
        return chatClientBuilder.
                build();
    }

    @Bean
    ApplicationRunner go(ChatClient chatClient) {
        return _ -> {
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

    // Try asking question: Plan a trip to Disney World
    // With and witout following tool. It will ask you to provide more details about your trip.
    @Bean
    ChatClientBuilderCustomizer addQuestionTool() {
        return builder -> {
            log.info("Adding QuestionTool to ChatClientBuilderCustomizer");
            builder
                .defaultTools(
                        AskUserQuestionTool.builder()
                                .questionHandler(new CommandLineQuestionHandler())
                                .build()
                );
        };
    }
}
