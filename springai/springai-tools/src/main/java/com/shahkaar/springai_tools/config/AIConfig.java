package com.shahkaar.springai_tools.config;

import com.shahkaar.springai_tools.service.SpringCloudAWSVersionFinderService;
import com.shahkaar.springai_tools.tools.MavenMetaDataTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.ChatClientBuilderCustomizer;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class AIConfig {

    @Bean
    ChatClient chatClient(ChatClient.Builder chatClientBuilder) {

        log.info("Creating ChatClient");
        return chatClientBuilder.
                build();
    }

    @Bean
    ChatClientBuilderCustomizer chatMemoryCustomizer() {

        return builder -> {
            log.info("Adding MessageChatMemoryAdvisor with maxMessages=5 to ChatClientBuilderCustomizer");
            builder.defaultAdvisors(
                    MessageChatMemoryAdvisor.builder(
                                    MessageWindowChatMemory.builder()
                                            .maxMessages(5)
                                            .build())
                            .build());
        };
    }

    @Bean
    ChatClientBuilderCustomizer loggingAdvisor() {

        return builder -> {
            log.info("Adding Logging Advisor to ChatClientBuilderCustomizer");
            builder.defaultAdvisors(SimpleLoggerAdvisor.
                                        builder().
                                        order(2).
                                        build())
                            .build();
        };
    }

    @Bean
    ChatClientBuilderCustomizer defaultTools(MavenMetaDataTool mavenMetaDataTool) {

        return builder -> {
            log.info("Adding MavenMetaDataTools to ChatClientBuilderCustomizer");
            builder.defaultTools(mavenMetaDataTool);
        };
    }

    @Bean
    ChatClientBuilderCustomizer systemEngineer() {

        return builder -> {
            log.info("Setting system prompt on ChatClientBuilderCustomizer");
            builder.defaultSystem(SpringCloudAWSVersionFinderService.SYSTEM_PROMPT);
        };
    }


}
