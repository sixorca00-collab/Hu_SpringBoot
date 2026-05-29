package com.hu.huspring.dtos;

import com.hu.huspring.EventType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

public record EventDTO(
        @Schema(description = "Event identifier", example = "12")
        Long id,
        @Schema(description = "Event type", example = "CONCERT")
        EventType type,
        @Schema(description = "Event start date")
        Date startDate,
        @Schema(description = "Event end date")
        Date endDate,
        @Schema(description = "Event description", example = "Concierto de ROCK")
        String desc,
        @Schema(description = "Linked venue id", example = "1")
        Long venueId,
        @Schema(description = "Venue address", example = "Centro de Convenciones Bogota")
        String venueAddress,
        @Schema(description = "Venue city", example = "Bogota")
        String venueCity,
        @Schema(description = "Linked category id", example = "1")
        Long categoryId,
        @Schema(description = "Category name", example = "ROCK")
        String categoryName) {

}
