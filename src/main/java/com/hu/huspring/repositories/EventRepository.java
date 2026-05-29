package com.hu.huspring.repositories;

import com.hu.huspring.dtos.EventDTO;
import com.hu.huspring.models.Event;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByDescContaining(String text);
    @Query("""
            SELECT new com.hu.huspring.dtos.EventDTO(
                e.id, e.type, e.startDate, e.endDate, e.desc,
                v.id, v.address, v.city,
                c.id, c.name
            )
            FROM Event e
            LEFT JOIN e.venue v
            LEFT JOIN e.category c
            WHERE (:category IS NULL OR LOWER(c.name) LIKE LOWER(CONCAT('%', :category, '%')))
              AND (:city IS NULL OR LOWER(v.city) LIKE LOWER(CONCAT('%', :city, '%')))
            ORDER BY e.startDate DESC
            """)
    Slice<EventDTO> findByFilters(
            @Param("category") String category,
            @Param("city") String city,
            Pageable pageable);
}
