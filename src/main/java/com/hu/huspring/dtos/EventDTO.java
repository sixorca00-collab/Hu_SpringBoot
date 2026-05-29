package com.hu.huspring.dtos;

import com.hu.huspring.EventType;

import java.util.Date;

public record EventDTO(Long id, EventType type, Date startDate, Date endDate, String desc, Long venueId, String venueAddress, String venueCity) {

}
