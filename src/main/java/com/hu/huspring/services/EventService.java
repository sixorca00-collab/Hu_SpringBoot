package com.hu.huspring.services;

import com.hu.huspring.models.Event;
import com.hu.huspring.repositories.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository repository;

    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    public Event save(Event event) {

        if (event.getType() == null) {
            throw new IllegalArgumentException("the Event type is mandatory");
        }

        if (event.getStartDate() == null) {
            throw new IllegalArgumentException("the StartDate is mandatory");
        }

        if (event.getEndDate() == null) {
            throw new IllegalArgumentException("the endDate is Mandatory");
        }

        if (event.getEndDate().before(event.getStartDate())) {
            throw new IllegalArgumentException(
                    "the end date can't be earlier to star date"
            );
        }

        return repository.save(event);
    }

    public List<Event> getAll() {
        return repository.getAll();
    }

    public Optional<Event> getById(Long id) {
        return repository.getById(id);
    }

    public boolean deleteById(Long id) {
        return repository.deleteById(id);
    }

    public Event updateById(Long id, Event updatedEvent) {

        return repository.updateById(id, updatedEvent);
    }
}