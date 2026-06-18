package com.shahkaar.helloworld.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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

    public String chat(String chatId, String cityName) {

        log.info("==> HelloSpringAI.chat service call. chatId: {}, cityName: {}", chatId, cityName);

        String response = chatClient
                //.prompt(message)      // Use this option for legacy code.
                .prompt()
                .user(u -> u.text("I am visiting {city}. What are the top 3 destination places?")
                        .param("city", cityName))
                .call()
                .content();

        Assert.notNull(response, "Null response");
        log.info(response);
        return response;
    }
}
