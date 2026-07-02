package com.shahkaar.assitant_agent.service;

import com.shahkaar.assitant_agent.model.dto.CommandResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class ChatService {

    private final ChatClient chatClient;
    //private final VectorStore vectorStore;
    private final RAGSearchService ragSearchService;

    private static final String RAG_PROMPT_TEMPLATE = """
        You are DevOpsGPT, a helpful AI assistant. Your user is asking a question about DevOps or Cloud topics.
        Use the information from the "DOCUMENTS" section to provide a detailed and accurate answer.
        If the documents do not contain the answer, state that you don't have enough information from the knowledge base.
        Do not make up information.

        DOCUMENTS:
        {documents}

        USER'S QUESTION:
        {input}
        """;

    public ChatService(ChatClient.Builder chatClientBuilder,
                            //VectorStore vectorStore,
                            RAGSearchService ragSearchService) {

        this.chatClient = chatClientBuilder.
                defaultAdvisors(new SimpleLoggerAdvisor(2)).
                build();
        //this.vectorStore = vectorStore;
        this.ragSearchService = ragSearchService;
    }

    public CommandResponse getRagReply(String userMessage) {

        log.info("Received RAG user message: '{}'", userMessage);

        // Retrieve relevant documents from the vector store based on the user's prompt
        String documentsText =  ragSearchService.searchRelevantDocumentsAsString(userMessage);

        // Create a prompt with the retrieved documents and user question
        PromptTemplate promptTemplate = new PromptTemplate(RAG_PROMPT_TEMPLATE);
        Prompt prompt = promptTemplate.create(Map.of(
                "documents", documentsText,
                "input", userMessage
        ));

        // 3. Send the enhanced prompt to the LLM
        return chatClient.
                        prompt(prompt).
                        call().
                        entity(CommandResponse.class);

    }

}
