package com.hu.huspring.repositories;

import com.hu.huspring.models.Event;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EventRepository implements CrudRepository<Event, Long> {

    private final List<Event> events = new ArrayList<>();

    @Override
    public Event save(Event event) {
        events.add(event);
        return event;
    }

    @Override
    public List<Event> getAll() {
        return events;
    }

    @Override
    public Optional<Event> getById(Long id) {

        return events.stream()
                .filter(event -> id.equals(event.getId()))
                .findFirst();
    }

    @Override
    public boolean deleteById(Long id) {

        return events.removeIf(event ->
                id.equals(event.getId())
        );
    }

    @Override
    public Event updateById(Long id, Event updatedEvent) {

        Optional<Event> optionalEvent = getById(id);

        if (optionalEvent.isPresent()) {

            Event existingEvent = optionalEvent.get();

            existingEvent.setType(updatedEvent.getType());
            existingEvent.setStartDate(updatedEvent.getStartDate());
            existingEvent.setEndDate(updatedEvent.getEndDate());
            existingEvent.setDesc(updatedEvent.getDesc());

            return existingEvent;
        }

        return null;
    }
}