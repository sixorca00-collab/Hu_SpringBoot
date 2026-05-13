package com.hu.huspring.models;

import com.hu.huspring.EventType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    private Long id;
    private EventType type;
    private Date startDate;
    private Date endDate;
    String desc;
}

