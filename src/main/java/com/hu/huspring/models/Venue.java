package com.hu.huspring.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Venue {
    private Long id;
    private String address;
    private int floor;
    private double price;
    private int capacity;
}
