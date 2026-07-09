package com.shahkaar.graph_using_alibaba.graph;

import com.alibaba.cloud.ai.graph.OverAllState;
import com.alibaba.cloud.ai.graph.action.NodeAction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;

import java.util.Map;

@Slf4j
public class TechnicalSupportNode implements NodeAction {

    private final ChatClient chatClient;

    public TechnicalSupportNode(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public Map<String, Object> apply(OverAllState state) throws Exception {

        log.info("Handling technical question.");
        String message = state.value("user_question", String.class)
                .orElseThrow();

        String response = chatClient.prompt()
                .system("""
            You are a technical support assistant.
            Write a brief, helpful response to the customer.
            Suggest one or two practical troubleshooting steps or
            ask if they have tried turning it off and then back on again.
            """)
                .user(message)
                .call()
                .content();

        assert response != null;
        return Map.of("response", response);
    }
}