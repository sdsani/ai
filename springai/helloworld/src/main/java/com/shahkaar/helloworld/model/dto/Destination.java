package com.shahkaar.helloworld.model.dto;

import lombok.Builder;

@Builder
public record Destination(String name,
                          String description,
                          String category,
                          Double rating
) {}
