package com.shahkaar.agents;

import com.google.adk.agents.BaseAgent;
import com.google.adk.agents.LlmAgent;
import com.google.genai.types.GenerateContentConfig;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;

import com.google.genai.types.Schema;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

@Slf4j
public class AgentWithClearInstructionsAndStructuredOP {

    // https://adk.dev/agents/llm-agents/#java_4
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

    public static BaseAgent ROOT_AGENT = initAgent();

    private static BaseAgent initAgent() {
        String instructions = loadInstructions();
        log.info("Initializing AgentWithClearInstructionsAndStructuredOP...");

        return LlmAgent.builder()
                .name("support_specialist")    // Agent Identity
                .description("Professional customer support agent with clear role definition and boundaries")
                .instruction(instructions)
                .outputSchema(REQUEST_OUTPUT)
                .outputKey("request_output") // Store result in state.get("found_capital")
                .model("gemini-flash-latest")
                .generateContentConfig(GenerateContentConfig.builder()
                        .temperature(0.2F) // More deterministic output
                        .maxOutputTokens(500)
                        .build())
                .build();
    }

    private static String loadInstructions() {
        try (InputStream inputStream = AgentWithClearInstructions.class
                .getClassLoader()
                .getResourceAsStream("agent_instructions.txt")) {

            if (inputStream == null) {
                throw new IllegalStateException("Missing resource: agent_instructions.txt");
            }

            return new String(inputStream.readAllBytes(), UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load agent instructions", e);
        }
    }

}
