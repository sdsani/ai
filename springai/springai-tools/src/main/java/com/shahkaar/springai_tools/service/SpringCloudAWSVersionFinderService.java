package com.shahkaar.springai_tools.service;

import com.shahkaar.springai_tools.model.dto.ArtifactVersionDto;
import com.shahkaar.springai_tools.tools.MavenMetaDataTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@Slf4j
public class SpringCloudAWSVersionFinderService {

    private static final String SYSTEM_PROMPT = """
                            You are system engineer. Working on upgrading a Spring boot application.
                            Spring cloud AWS is one of the framework part of your application.
                            You want to find the latest Spring Cloud AWS version available out there.""";

    private final ChatClient chatClient;
    private final MavenMetaDataTool mavenMetaDataTool;

    SpringCloudAWSVersionFinderService(ChatClient.Builder builder,
                                       MavenMetaDataTool mavenMetaDataTool,
                                       ChatMemory chatMemory) {
        this.mavenMetaDataTool = mavenMetaDataTool;

        // Setting tools under .defaultTools() will make the tool available for all prompts.
        chatClient = builder
                .defaultSystem( SYSTEM_PROMPT )
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                //.defaultTools(mavenMetaDataTool)
                .build();
        log.info("Chat client has been built {}", "SpringCloudAWSVersionFinder");
    }

    public String chat(String chatId, String groupId, String artifactId) {

        log.info("==> SpringCloudAWSVersionFinder.chat service call. groupID: {}, artifactId: {}", groupId, artifactId);

        // Setting tools under .tools() will make the tool available for this specific prompt.
        ArtifactVersionDto response = chatClient
                .prompt(
                        String.format("Find the latest of spring-cloud-aws-dependencies using using Maven metadata (groupId: %s, artifactId: %s) ",
                                groupId,
                                artifactId)
                )
                .tools(mavenMetaDataTool)  // Added to the tools
                .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, chatId))
                .call()
                .entity(ArtifactVersionDto.class);      // Asking to transform response into a Java object

        Assert.notNull(response, "Null response");
        log.info(response.toString());
        return response.toString();
    }
}
