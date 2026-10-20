package com.shahkaar.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ConfigurationProperties(prefix = "adk")
public class AdkProperties {

    private String appName;
    private String userId;
    private Map<String, Object> initialState = new HashMap<>();
    private Resource instructionsFile;

    public String loadInstructions() throws IOException {

        try (var inputStream = instructionsFile.getInputStream()) {

            return new String(
                    inputStream.readAllBytes(),
                    StandardCharsets.UTF_8);
        }
    }
}
