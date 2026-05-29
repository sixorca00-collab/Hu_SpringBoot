package com.hu.huspring.models;

import com.hu.huspring.EventType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Events")
@Entity

@SQLRestriction("isActive = true")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private EventType type;

    // Fusionadas las anotaciones de base de datos con las de formato HTML
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")//Asi debe capturar el formato para evitar mismath exception
    private Date endDate;

    @Column(name = "description")
    private String desc;

    @Column(name = "isActive")
    private boolean isActive = true;

    // Aquí vive la relación que recuperamos de Git
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "venue_id", nullable = true)
    private Venue venue;

    public void softDelete(){this.isActive = false;}
}