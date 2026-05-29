package com.hu.huspring.dtos;

import java.util.Date;

public record EventDTO(Long id,String name, Date startDate, String venueName, String venueCity) {

}
