package com.shahkaar.assitant_agent.model.dto;

import lombok.Builder;

@Builder
public record CommandResponse(String command, String explanation) {
}
