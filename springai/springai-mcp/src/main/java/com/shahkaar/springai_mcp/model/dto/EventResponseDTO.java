package com.shahkaar.springai_mcp.model.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record EventResponseDTO(List<EventDTO> events) {
    public List<EventDTO> getEvents() {
        return events;
    }
}
