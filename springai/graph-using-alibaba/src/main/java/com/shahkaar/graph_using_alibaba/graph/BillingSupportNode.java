package com.shahkaar.graph_using_alibaba.graph;

import com.alibaba.cloud.ai.graph.OverAllState;
import com.alibaba.cloud.ai.graph.action.NodeAction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;

import java.util.Map;

@Slf4j
public class BillingSupportNode implements NodeAction {

    private final ChatClient chatClient;

    public BillingSupportNode(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public Map<String, Object> apply(OverAllState state) throws Exception {
        log.info("Handling billing question.");
        String question = state.value("user_question", String.class)
                .orElseThrow();
        String feedback = state.value("feedback", "");

        String userMessage = """
                            Customer request:
                            %s
                            """.formatted(question);

        if (!feedback.isBlank()) {
            userMessage += """
      
                          Previous feedback on your last response:
                          %s
                          
                          Revise your answer to address this feedback.
                          """.formatted(feedback);
        }

        var response = chatClient.prompt()
                .system("""
            You are a billing support assistant.
            Write a brief, helpful response to the customer.
            Do not promise refunds. Say that the billing team will review the account.
            """)
                .user(userMessage)
                .call()
                .content();

        assert response != null;
        return Map.of("response", response);
    }

}