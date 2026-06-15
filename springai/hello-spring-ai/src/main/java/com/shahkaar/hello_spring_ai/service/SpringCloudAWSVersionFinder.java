package com.shahkaar.hello_spring_ai.service;

import com.shahkaar.hello_spring_ai.model.dto.ArtifactVersionDto;
import com.shahkaar.hello_spring_ai.tools.MavenMetaDataTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@Slf4j
public class SpringCloudAWSVersionFinder {

    private final ChatClient chatClient;

    SpringCloudAWSVersionFinder(ChatClient.Builder builder,
                                MavenMetaDataTool mavenMetaDataTool) {
        chatClient = builder
                .defaultSystem("""
                            You are system engineer. Working on upgrading a Spring boot application.
                            Spring cloud AWS is one of the framework part of your application.
                            You want to find the latest Spring Cloud AWS version available out there.""")
                .defaultTools(mavenMetaDataTool)
                .build();
        log.info("Chat client has been built {}", "SpringCloudAWSVersionFinder");
    }

    private static final String systemPrompt = """
                            You are system engineer. Working on upgrading a Spring boot application.
                            Spring cloud AWS is one of the framework part of your application.
                            You want to find the latest Spring Cloud AWS version available out there.""";

    public String findLatestVersion(String groupId, String artifactId) {

        log.info("==> SpringCloudAWSVersionFinder.findLatestVersion service call. groupID: {}, artifactId: {}", groupId, artifactId);
        ArtifactVersionDto response = chatClient
                .prompt(
                        String.format("Find the latest of spring-cloud-aws-dependencies using using Maven metadata (groupId: %s, artifactId: %s) ",
                                groupId,
                                artifactId)
                )
                //.tools(new MavenMetaDataExtractorTool(rcBuilder))  // Added to the tools
                .call()
                .entity(ArtifactVersionDto.class);      // Asking to transform response into a Java object

        Assert.notNull(response, "Null response");
        log.info(response.toString());
        return response.toString();
    }
}
