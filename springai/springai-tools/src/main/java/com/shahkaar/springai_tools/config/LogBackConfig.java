package com.shahkaar.springai_tools.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.restclient.RestClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.logbook.spring.LogbookClientHttpRequestInterceptor;

@Configuration
@Slf4j
public class LogBackConfig {

    @Bean
    RestClientCustomizer logbookCustomizer(LogbookClientHttpRequestInterceptor interceptor) {
        return restClient -> restClient.requestInterceptor(interceptor);
    }
}
