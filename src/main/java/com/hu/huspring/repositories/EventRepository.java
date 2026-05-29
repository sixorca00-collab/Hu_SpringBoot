package com.hu.huspring.repositories;

import com.hu.huspring.dtos.EventDTO;
import com.hu.huspring.models.Event;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByDescContaining(String text);
    @Query("SELECT new com.hu.huspring.dto.EventDTO(e.id, e.n, e.startDate, v.name, v.city) FROM Event e JOIN e.venue v")
    Slice<EventDTO> findByFilters(Pageable pageable);
}