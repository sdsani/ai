package com.shahkaar.hello_spring_ai.workflows;

import org.springframework.ai.chat.client.ChatClient;

public class ChainWorkFlow {

    private static final String[] DEFAULT_SYSTEM_PROMPTS = {

            // Step 1
            """
                Find the latest springcloudaws version""",
//            """
//                Use Spring cloud AWS version and find a supported latest version of Spring Boot.
//                For compatability validation, use table under 'Compatibility with Spring Project Versions' section
//                on this page 'https://github.com/awspring/spring-cloud-aws'""",
            """
                Report both Spring Cloud AWS and Spring Boot versions in the format below:
                | FrameWork | Version | Release Date |
                |:----------|:--------|:-------------|
                |Spring Cloud AWS| 4.0.2 | mm-dd-yyyy |
            """
    };

    private final ChatClient chatClient;
    private final String[] systemPrompts;

    public ChainWorkFlow(ChatClient chatClient) {
        this(chatClient, DEFAULT_SYSTEM_PROMPTS);
    }

    public ChainWorkFlow(ChatClient chatClient, String[] systemPrompts) {
        this.chatClient = chatClient;
        this.systemPrompts = systemPrompts;
    }

    public String chain() {

        Integer step = 0;
        //String response = userInput;
        String response = "";

        System.err.println( stringFormat(step, response) );

        String input;
        for (String prompt : systemPrompts) {
            // 1. Compose the input using the response from the prviouse step.
            input = String.format("{%s}\n {%s}", prompt, response);

            // 2. Call the chat client with the new input and get the new response.
            response = chatClient.prompt(input).call().content();
            System.out.println( stringFormat(step, response) );
        }
        return response;
    }

    private String stringFormat(Integer step, String response) {
        return String.format("\nSTEP %s:\n %s", step++, response);
    }

}
