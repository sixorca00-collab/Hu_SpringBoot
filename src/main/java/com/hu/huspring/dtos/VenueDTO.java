package com.hu.huspring.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

public record VenueDTO(
        @Schema(description = "Venue identifier", example = "1")
        Long id,
        @Schema(description = "Venue address", example = "Centro de Convenciones Bogota")
        String address,
        @Schema(description = "Venue floor or level", example = "2")
        int floor,
        @Schema(description = "Venue rental price", example = "4500.0")
        double price,
        @Schema(description = "Venue capacity", example = "1000")
        int capacity) {
}
