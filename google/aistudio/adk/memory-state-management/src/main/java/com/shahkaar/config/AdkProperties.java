package com.shahkaar.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ConfigurationProperties(prefix = "adk")
public class AdkProperties {

    private String appName;
    private String userId;
    private Map<String, Object> initialState = new HashMap<>();
}
