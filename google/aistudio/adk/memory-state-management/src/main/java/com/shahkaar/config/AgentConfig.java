package com.shahkaar.config;

import com.google.adk.agents.BaseAgent;
import com.google.adk.agents.LlmAgent;
import com.google.genai.types.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Map;

import static com.google.genai.types.HarmBlockThreshold.Known.BLOCK_MEDIUM_AND_ABOVE;
import static com.google.genai.types.HarmCategory.Known.HARM_CATEGORY_DANGEROUS_CONTENT;

@Configuration
@EnableConfigurationProperties(AdkProperties.class)
@Slf4j
public class AgentConfig {

    private static final Schema REQUEST_OUTPUT =
            Schema.builder()
                    .type("OBJECT")
                    .description("Schema for customer support information.")
                    .properties(
                            Map.of(
                                    "response",
                                    Schema.builder()
                                            .type("STRING")
                                            .description("Answer of your question is.")
                                            .build()))
                    .build();

    @Bean
    public BaseAgent rootAgent(AdkProperties adkProperties) throws IOException {

        log.info(adkProperties.loadInstructions());
        return LlmAgent.builder()
                .name("support_specialist")    // Agent Identity
                .description("Professional customer support agent with clear role definition and boundaries")
                .instruction(adkProperties.loadInstructions())
                .outputSchema(REQUEST_OUTPUT)
                .outputKey("response") // Store result in state
                .model("gemini-flash-latest")
                .generateContentConfig(GenerateContentConfig.builder()
                        .temperature(0.2F) // More deterministic output
                        .maxOutputTokens(250)
                        .topP(.95F)  // # Consider top 95% of probability mass
                        .topK(40F) // # Consider top 40 tokens
                        .safetySettings(SafetySetting.builder()
                                .category(HARM_CATEGORY_DANGEROUS_CONTENT)
                                .threshold(BLOCK_MEDIUM_AND_ABOVE))
                        .thinkingConfig(ThinkingConfig.builder()
                                .includeThoughts(true)
                                //.thinkingBudget(1024)  // You can set either thinkingLevel or thinkingBudget, but not both.
                                .thinkingLevel(ThinkingLevel.Known.LOW)   // You can set either thinkingLevel or thinkingBudget, but not both.
                        )
                        .build())
                .build();
    }
}
