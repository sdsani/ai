package com.shahkaar.text_based_chat_loop.config;

import lombok.extern.slf4j.Slf4j;
import org.springaicommunity.agent.tools.AskUserQuestionTool;
import org.springaicommunity.agent.tools.TodoWriteTool;
import org.springaicommunity.agent.utils.CommandLineQuestionHandler;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.ChatClientBuilderCustomizer;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    // Make sure to set this property: app.features.question-tool = true
    @Bean
    @ConditionalOnProperty(name = "app.features.question-tool.enabled", havingValue = "true")
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

    // Try asking question: Plan to replace spring boot with Quarkus in my application
    // With and witout following tool. It will ask you to provide more details about your trip.
    // Make sure to set this property: app.features.todo-write-tool = true
//    @Bean
//    @ConditionalOnProperty(name = "app.features.todo-write-tool.enabled", havingValue = "true", matchIfMissing = false)
//    ChatClientBuilderCustomizer todoWriteTool() {
//
//        log.info("Adding TodoWriteTool to ChatClientBuilderCustomizer");
//        return builder -> builder
//                .defaultTools(
//                        TodoWriteTool.builder()
//                                .build());
//    }

    // Try asking question: Compare Apollo 11, Apollo 13, and Apollo 17. Break it into steps and produce a final report.
    // Or: Find the top 10 Tom Hanks movies, then group them in groups of 2, and finally print the title name in inverted (e.g. last char first). Use TodoWrite to organize your tasks.
    // With and witout following tool. It will ask you to provide more details about your trip.
    // Make sure to set this property: app.features.todo-write-tool = true
    @Bean
    @ConditionalOnProperty(name = "app.features.todo-write-tool.enabled", havingValue = "true")
    ChatClientBuilderCustomizer todoWriteTool() {
        return builder -> builder
                .defaultTools(
                        TodoWriteTool.builder()
                                .todoEventHandler(event -> {
                                    var todos = event.todos();
                                    var completeCount = todos.stream()
                                            .filter(todo ->
                                                    todo.status().equals(
                                                            TodoWriteTool.Todos.Status.completed))
                                            .count();
                                    var percentageComplete =
                                            Math.round((completeCount * 100.0) / todos.size());
                                    log.info("Event ({}/{} : {}%):",
                                            completeCount,
                                            todos.size(),
                                            percentageComplete);
                                    todos.forEach(todoItem -> log.info("   -- TODO Item: {} - {}",
                                            todoItem.status(),
                                            todoItem.content()));
                                })
                                .build());
    }
}
