package com.shahkaar.helloworld.service;

import com.shahkaar.helloworld.model.dto.TopDestinationsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import reactor.core.publisher.Flux;

@Service
@Slf4j
public class HelloSpringAI {

    private static final String SYSTEM_PROMPT = """
                            You are a friendly travel agent. answer in a concise, professional tone.""";

    private final ChatClient chatClient;

    HelloSpringAI(ChatClient.Builder builder) {

        chatClient = builder
                .defaultSystem( SYSTEM_PROMPT )
                .build();
        log.info("Chat client has been built {}", "HelloSpringAI");
    }

    // Sync call
    public TopDestinationsResponse chat(String chatId, String cityName) {

        log.info("==> HelloSpringAI.chat service call. chatId: {}, cityName: {}", chatId, cityName);

        TopDestinationsResponse response = chatClient
                //.prompt(message)      // Use this option for legacy code.
                .prompt()
                .user(u -> u.text("I am visiting {city}. What are the top 3 destination places?")
                        .param("city", cityName))
                .call()
                .entity(TopDestinationsResponse.class);

        Assert.notNull(response, "Null response");
        log.info(response.toString());
        return response;
    }

    // ASync call
    public Flux<String> aSyncChat(String chatId, String cityName) {

        log.info("==> HelloSpringAI.aSyncChat service call. chatId: {}, cityName: {}", chatId, cityName);

        return chatClient
                //.prompt(message)      // Use this option for legacy code.
                .prompt()
                .user(u -> u.text("I am visiting {city}. What are the top 3 destination places?")
                        .param("city", cityName))
                .stream()
                .content();
    }

}
