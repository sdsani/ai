package com.shahkaar.helloworld.model.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record TopDestinationsResponse(String city,
                                      List<Destination> destinations
                                    ) {}
