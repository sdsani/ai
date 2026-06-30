package com.shahkaar.springai_rag.service;

import com.shahkaar.springai_rag.model.dto.CompanyOfficeHoursDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@Slf4j
public class OfficeHourService {

    private final ChatClient chatClient;

    private static final String SYSTEM_PROMPT = """
            You are useful assistant and can provide office hours for different companies when company name is provided
            If the answer is not in the context, say "I do not know".
            CONTEXT:
            {question_answer_context}
            QUESTION:
            {query}
            """;

    OfficeHourService(ChatClient.Builder builder, VectorStore vectorStore) {

        MessageChatMemoryAdvisor messageChatMemoryAdvisor = MessageChatMemoryAdvisor
                .builder(MessageWindowChatMemory.builder()
                        .build())
                .order(0)
                .build();

        chatClient = builder
                .defaultSystem( SYSTEM_PROMPT )
                .defaultAdvisors(new SimpleLoggerAdvisor(2),
                                        messageChatMemoryAdvisor,
                                        QuestionAnswerAdvisor.
                                                builder(vectorStore).
                                                promptTemplate(PromptTemplate.builder().template(SYSTEM_PROMPT).build()).
                                                build()
                                ).build();

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
