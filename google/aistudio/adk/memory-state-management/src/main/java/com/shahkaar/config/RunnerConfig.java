package com.shahkaar.config;

import com.google.adk.agents.BaseAgent;
import com.google.adk.agents.RunConfig;
import com.google.adk.runner.Runner;
import com.google.adk.sessions.InMemorySessionService;
import com.google.genai.types.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(AdkProperties.class)
@Slf4j
public class RunnerConfig {

    @Bean
    public InMemorySessionService sessionService() {
        return new InMemorySessionService();
    }

    @Bean
    public RunConfig runConfig() {
        return RunConfig.builder().build();
    }

    @Bean
    public Runner runner(
            BaseAgent rootAgent,
            AdkProperties properties,
            InMemorySessionService sessionService) {

        return Runner.builder()
                .agent(rootAgent)
                .appName(properties.getAppName())
                .sessionService(sessionService)
                .build();
    }
}
