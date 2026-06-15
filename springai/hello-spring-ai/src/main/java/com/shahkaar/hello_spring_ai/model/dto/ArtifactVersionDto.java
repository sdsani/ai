package com.shahkaar.hello_spring_ai.model.dto;

import lombok.Builder;

@Builder
public record ArtifactVersionDto(
        String groupId,
        String artifactId,
        String version,
        String lastUpdated
) {}
