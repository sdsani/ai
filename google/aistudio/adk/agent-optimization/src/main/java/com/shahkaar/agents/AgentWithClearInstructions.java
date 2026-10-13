package com.shahkaar.agents;

import com.google.adk.agents.BaseAgent;
import com.google.adk.agents.LlmAgent;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import static java.nio.charset.StandardCharsets.UTF_8;

@Slf4j
public class AgentWithClearInstructions {

    public static BaseAgent ROOT_AGENT = initAgent();

    private static BaseAgent initAgent() {
        String instructions = loadInstructions();
        log.info("Initializing AgentWithClearInstructions...");

        return LlmAgent.builder()
                .name("support_specialist")
                .description("Professional customer support agent with clear role definition and boundaries")
                .instruction(instructions)
                .model("gemini-flash-latest")
                //.tools(FunctionTool.create(AgentWithClearInstructions.class, "getCurrentTime"))
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
