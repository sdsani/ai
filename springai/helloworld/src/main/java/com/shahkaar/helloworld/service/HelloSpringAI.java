package com.shahkaar.helloworld.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@Slf4j
public class HelloSpringAI {

    private static final String SYSTEM_PROMPT = """
                            You are system engineer. Working on upgrading a Spring boot application.
                            Spring cloud AWS is one of the framework part of your application.
                            You want to find the latest Spring Cloud AWS version available out there.""";

    private final ChatClient chatClient;

    HelloSpringAI(ChatClient.Builder builder) {

        chatClient = builder
                .defaultSystem( SYSTEM_PROMPT )
                .build();
        log.info("Chat client has been built {}", "HelloSpringAI");
    }

    public String chat(String chatId, String message) {

        log.info("==> HelloSpringAI.chat service call. chatId: {}, message: {}", chatId, message);

        String response = chatClient
                .prompt(message)
                .call()
                .content();

        Assert.notNull(response, "Null response");
        log.info(response);
        return response;
    }
}
