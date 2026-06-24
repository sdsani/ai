package com.shahkaar.springai_mcp.model.dto;

import lombok.Builder;

import java.util.Date;

@Builder
public record EventDTO(String eventName,
                       Date eventDate,
                       String url,
                       String title,
                       String extraSnippet) {
}
