package com.hu.huspring.services;

import com.hu.huspring.dtos.EventDTO;
import com.hu.huspring.models.Event;
import com.hu.huspring.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Event save(Event event) {
        if (event.getType() == null || event.getStartDate() == null || event.getEndDate() == null) {
            throw new IllegalArgumentException("Ningún campo obligatorio puede ser nulo");
        }
        if (event.getEndDate().before(event.getStartDate())) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la de inicio");
        }
        return eventRepository.save(event);
    }

    /*public List<Event> getAll() {
        return eventRepository.findAll();
    }*/

    public Slice<EventDTO> getAllPaginated(Pageable pageable) {
        return getAllPaginated(pageable, null, null);
    }

    public Slice<EventDTO> getAllPaginated(Pageable pageable, String category, String city) {
        String categoryFilter = normalizeFilter(category);
        String cityFilter = normalizeFilter(city);
        return eventRepository.findByFilters(categoryFilter, cityFilter, pageable); // JPA hace la magia de cortar el pastel en porciones por detrás
    }

    public long count() {
        return eventRepository.count();
    }

    public Optional<Event> getById(Long id) {
        return eventRepository.findById(id);
    }

    public boolean deleteById(Long id) {
        return eventRepository.findById(id).map(event -> {
            event.softDelete();
            eventRepository.save(event);
            return true;
        }).orElse(false);
    }

    public Event updateById(Long id, Event entity) {
        return eventRepository.findById(id).map(existingEvent -> {
            existingEvent.setType(entity.getType());
            existingEvent.setStartDate(entity.getStartDate());
            existingEvent.setEndDate(entity.getEndDate());
            existingEvent.setDesc(entity.getDesc());
            existingEvent.setVenue(entity.getVenue()); // <-- Guardamos la relación nueva
            return eventRepository.save(existingEvent);
        }).orElse(null);
    }

    private String normalizeFilter(String value) {
        if (value == null) {
            return null;
        }

        String trimmedValue = value.trim();
        return trimmedValue.isEmpty() ? null : trimmedValue;
    }
}
