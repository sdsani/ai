package com.shahkaar.springai_rag.model.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record CompanyOfficeHours(String companyName,
                                 String CompanyAddress,
                                 List<OfficeHourDTO> officeHours) {
}
