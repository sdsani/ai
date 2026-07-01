package com.shahkaar.assitant_agent.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record ChatRequest(@NotBlank(message = "User message cannot be blank.") String prompt,
                          @NotBlank(message = "Session ID cannot be blank.") String sessionId) {
//public record ChatRequest(@NotBlank(message = "User message cannot be blank.") String prompt) {
}