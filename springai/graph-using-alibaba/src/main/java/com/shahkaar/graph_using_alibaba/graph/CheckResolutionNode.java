package com.shahkaar.graph_using_alibaba.graph;

import com.alibaba.cloud.ai.graph.OverAllState;
import com.alibaba.cloud.ai.graph.action.NodeAction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;

import java.util.Map;

@Slf4j
public class CheckResolutionNode implements NodeAction {

    private final ChatClient chatClient;

    public CheckResolutionNode(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public Map<String, Object> apply(OverAllState state) {

        String userQuestion = state.value("user_question", "");
        String response = state.value("response", "");
        int attempts = state.value("attempts", 0);

        log.trace("Attempt {} to resolve {}", attempts+1, userQuestion);

        Evaluation evaluation = chatClient.prompt()
                .system("""
            You are evaluating a customer support response.

            Determine whether the response adequately resolves
            the customer's request.

            A good response should:
            - directly address the user's issue
            - provide clear, actionable steps
            - avoid vague or generic advice

            If the response is insufficient, explain briefly what is missing.

            Respond ONLY as JSON:

            {
              "resolved": true|false,
              "feedback": "..."
            }
            """)
                .user("""
            Customer request:
            %s

            Support response:
            %s
            """.formatted(userQuestion, response))
                .call()
                .entity(Evaluation.class);

        assert evaluation != null;
        return Map.of(
                "resolved", evaluation.resolved(),
                "feedback", evaluation.feedback(),
                "attempts", attempts + 1
        );
    }

    public record Evaluation(
            boolean resolved,
            String feedback
    ) {}
}