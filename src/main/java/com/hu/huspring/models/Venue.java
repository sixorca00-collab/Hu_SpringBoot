package com.hu.huspring.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Venues")

public class Venue {

    @Id //Define PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //funciona como un autoincrement
    private Long id;
    private String address;
    private int floor;
    private double price;
    private int capacity;
}
