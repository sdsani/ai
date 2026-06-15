package com.shahkaar.hello_spring_ai.config;

import com.shahkaar.hello_spring_ai.model.dto.ArtifactVersionDto;
import com.shahkaar.hello_spring_ai.tools.MavenMetaDataTool;
import com.shahkaar.hello_spring_ai.workflows.ChainWorkFlow;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;
import org.springframework.web.client.RestClient;

@Configuration
@Slf4j
public class AIConfig {

    //@Bean
    public CommandLineRunner randomCity(ChatClient.Builder builder) {
        return args -> {

            // ChatClient allow us to hold a conversation with LLM
            ChatClient chatClient = builder.build();
            String response = chatClient
                    .prompt("Pick a random city. Tell me a fun fact for the city and the country it is in.")
                    .call()
                    .content();
            log.info(response);
        };
    }

    //@Bean
    public CommandLineRunner commandLineRunner(ChatClient.Builder chatClientBuilder) {
        return args -> {
          new ChainWorkFlow(chatClientBuilder.build()).chain();
        };
    }

    @Bean
    public CommandLineRunner findLatestSpringCloudAWSVersion(ChatClient.Builder builder,
                                                             RestClient.Builder rcBuilder,
                                                             MavenMetaDataTool mavenMetaDataTool) {

        Assert.notNull(builder, "ChatClient.Builder is null");
        Assert.notNull(rcBuilder, "rcBuilder is null");

        return args -> {

            // ChatClient allow us to hold a conversation with LLM
            // System prompt to refine LLM behavior. System prompt defines Role, Context and tone.
            // You can refine System Prompt with restrictions. For example, you can ask that pick from 3.X or 4.X
            // since these could be two different paths.
            // **System prompt is a way to set the stage for the agent**
            ChatClient chatClient = builder
                    .defaultSystem("""
                            You are system engineer. Working on upgrading a Spring boot application.
                            Spring cloud AWS is one of the framework part of your application.
                            You want to find the latest Spring Cloud AWS version available out there.""")
                    .defaultTools(mavenMetaDataTool)
                    .build();
            log.info("findLatestSpringCloudAWSVersion2");

            // A conversation
            //String response = chatClient
            //        .prompt("Find the latest Spring Cloud AWS version using maven metadata.")
            //        .tools(new MavenMetaDataExtractorTool(rcBuilder))
            //        .call()
            //        .content();       // Default formating used in this case. String
            // Output of above method => Source: Maven metadata for io.awspring.cloud:spring-cloud-aws shows
            //          <latest>4.0.2</latest> and <release>4.0.2</release> (lastUpdated: 20260429220547).

            // Option 2, instead of simple String, let's format output to a format
            ArtifactVersionDto response = chatClient
                    .prompt("Find the latest Spring Cloud AWS version using maven metadata.")
                    //.tools(new MavenMetaDataExtractorTool(rcBuilder))  // Added to the tools
                    .call()
                    .entity(ArtifactVersionDto.class);      // Asking to transform response into a Java object

            Assert.notNull(response, "Null response");
            log.info(response.toString());
            // Response of this call is here
            // ArtifactVersionDto[groupId=io.awspring.cloud, artifactId=spring-cloud-aws, version=4.0.2, lastUpdated=20260429220547]
        };
    }
}
