package com.shahkaar.springai_tools.model.dto;

import lombok.Builder;

@Builder
public record ArtifactVersionDto(
        String groupId,
        String artifactId,
        String version,
        String lastUpdated
) {}
