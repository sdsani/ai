package com.shahkaar.springai_mcp.advisors;

import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.AdvisorChain;
import org.springframework.ai.chat.client.advisor.api.BaseAdvisor;
import tools.jackson.databind.ObjectMapper;

// This advisor is just to demonstrate how to implement a new advisor
@Slf4j
public class HelloAdvisor implements BaseAdvisor {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public int getOrder() {
        return 1;
    }

    @Override
    public @NonNull ChatClientRequest before(ChatClientRequest chatClientRequest, @NonNull AdvisorChain advisorChain) {
        print("====> HelloAdvisor REQUEST", chatClientRequest.prompt().getInstructions());
        return chatClientRequest;
    }

    @Override
    public @NonNull ChatClientResponse after(ChatClientResponse chatClientResponse, @NonNull AdvisorChain advisorChain) {
        assert chatClientResponse.chatResponse() != null;
        print("====> Hello Advisor RESPONSE", chatClientResponse.chatResponse().getResults());
        return chatClientResponse;
    }

    private void print(String label, Object object) {
        log.info("{} : {}{}", label ,this.objectMapper.writeValueAsString(object), "\n");
    }
}
