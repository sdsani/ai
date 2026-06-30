package com.shahkaar.springai_rag.model.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record CompanyOfficeHoursDTO(String companyName,
                                    String CompanyAddress,
                                    List<OfficeHourDTO> officeHours) {
}
