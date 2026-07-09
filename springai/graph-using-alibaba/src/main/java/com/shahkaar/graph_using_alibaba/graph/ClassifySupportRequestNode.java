package com.shahkaar.graph_using_alibaba.graph;

import com.alibaba.cloud.ai.graph.OverAllState;
import com.alibaba.cloud.ai.graph.action.NodeAction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;

import java.util.Map;
import java.util.Objects;

@Slf4j
public class ClassifySupportRequestNode implements NodeAction {

    private final ChatClient chatClient;

    public ClassifySupportRequestNode(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public Map<String, Object> apply(OverAllState state) throws Exception {

        var question = state.value("user_question", String.class)
                .orElseThrow();
        log.info("Classifying user question. Question: {}", question);

        var category = Objects.requireNonNull(chatClient.prompt()
                        .system("""
                                Classify the customer support request as exactly one of:
                                billing
                                technical
                                
                                Respond with only the category name.
                                """)
                        .user(question)
                        .call()
                        .content())
                .trim()
                .toLowerCase();

        if (!category.equals("billing") && !category.equals("technical")) {
            log.info("Question category({}) unknown. Defaulting to technical.", category);
            category = "technical";
        }

        return Map.of("category", category);
    }

}