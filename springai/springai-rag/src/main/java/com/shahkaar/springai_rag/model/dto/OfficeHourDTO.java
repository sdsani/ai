package com.shahkaar.springai_rag.model.dto;

import lombok.Builder;

@Builder
public record OfficeHourDTO(String dayOfTheWeek,
                            String officeHour
                            ) {
}
