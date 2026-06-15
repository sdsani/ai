package com.shahkaar.hello_spring_ai.tools;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@Slf4j
public class MavenMetaDataTool {

    private static final String SPRING_CLOUD_AWS_URL =
            "https://repo.maven.apache.org/maven2/io/awspring/cloud/spring-cloud-aws/maven-metadata.xml";

    private final RestClient restClient = RestClient.builder().build();

    // To integrate with the AI Agent and our LLM, I need to publish it as a tool
    // Next update the System prompt to direct LLM to use the provided tool.
    // You can pass parameters using @ToolParam annotation.
    @Tool(name = "getMavenData", description = "Get Maven Metadata for spring-cloud-aws")
    String getMavenData() {
        String xml = restClient.get()
                .uri(SPRING_CLOUD_AWS_URL)
                .retrieve()
                .body(String.class);
        log.info("<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>");
        log.info(xml);
        log.info("<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>");
        return xml;
    }
}