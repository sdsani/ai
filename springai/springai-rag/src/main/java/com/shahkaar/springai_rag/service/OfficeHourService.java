package com.shahkaar.springai_rag.service;

import com.shahkaar.springai_rag.model.dto.CompanyOfficeHoursDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@Slf4j
public class OfficeHourService {

    private final ChatClient chatClient;

    private static final String SYSTEM_PROMPT = """
            "You are useful assistant and can provide office hours for different companies when company name is provided""";

    OfficeHourService(ChatClient.Builder builder) {

        MessageChatMemoryAdvisor messageChatMemoryAdvisor = MessageChatMemoryAdvisor
                .builder(MessageWindowChatMemory.builder()
                        .build())
                .order(0)
                .build();

        // Although simple logging adviser is first in the list below, however,
        // Still it should trigger after HellowAdvisor since HellowAdvisor is set with order or 1.
        chatClient = builder
                .defaultSystem( SYSTEM_PROMPT )
                .defaultAdvisors(new SimpleLoggerAdvisor(2), messageChatMemoryAdvisor)
                .build();

        log.info("Chat client has been built {}", "OfficeHourService");
    }

    public CompanyOfficeHoursDTO chat(String chatId, String prompt) {
        log.info("==> BraveSearchService.chat service call. chatID: {}, prompt: {}", chatId, prompt);

        // Setting tools under .tools() will make the tool available for this specific prompt.
        CompanyOfficeHoursDTO response = chatClient
                .prompt(prompt)
                .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, chatId))
                .call()
                .entity(CompanyOfficeHoursDTO.class);      // Asking to transform response into a Java object

        Assert.notNull(response, "Response is null");
        log.info(response.toString());
        log.info("Events count: {}", response.officeHours().size());
        return response;
    }

}
