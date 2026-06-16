package com.shahkaar.hello_spring_ai.service;

import com.shahkaar.hello_spring_ai.model.dto.ArtifactVersionDto;
import com.shahkaar.hello_spring_ai.tools.MavenMetaDataTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.ai.chat.memory.ChatMemory;

@Service
@Slf4j
public class SpringCloudAWSVersionFinder {

    private static final String SYSTEM_PROMPT = """
                            You are system engineer. Working on upgrading a Spring boot application.
                            Spring cloud AWS is one of the framework part of your application.
                            You want to find the latest Spring Cloud AWS version available out there.""";

    private final ChatClient chatClient;

    SpringCloudAWSVersionFinder(ChatClient.Builder builder,
                                MavenMetaDataTool mavenMetaDataTool,
                                ChatMemory chatMemory) {
        chatClient = builder
                .defaultSystem( SYSTEM_PROMPT )
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .defaultTools(mavenMetaDataTool)
                .build();
        log.info("Chat client has been built {}", "SpringCloudAWSVersionFinder");
    }

    public String chat(String chatId, String groupId, String artifactId) {

        log.info("==> SpringCloudAWSVersionFinder.chat service call. groupID: {}, artifactId: {}", groupId, artifactId);
        ArtifactVersionDto response = chatClient
                .prompt(
                        String.format("Find the latest of spring-cloud-aws-dependencies using using Maven metadata (groupId: %s, artifactId: %s) ",
                                groupId,
                                artifactId)
                )
                //.tools(new MavenMetaDataExtractorTool(rcBuilder))  // Added to the tools
                .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, chatId))
                .call()
                .entity(ArtifactVersionDto.class);      // Asking to transform response into a Java object

        Assert.notNull(response, "Null response");
        log.info(response.toString());
        return response.toString();
    }
}
