package com.hu.huspring.services;

import com.hu.huspring.models.Event;
import com.hu.huspring.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<Event> getAllPaginated(Pageable pageable) {
        return eventRepository.findAll(pageable); // JPA hace la magia de cortar el pastel en porciones por detrás
    }

    public Optional<Event> getById(Long id) {
        return eventRepository.findById(id);
    }

    public boolean deleteById(Long id) {
        if (eventRepository.existsById(id)) {
            eventRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Event updateById(Long id, Event entity) {
        // AQUÍ REEMPLAZAMOS EL 'Venue' POR 'Event' PARA QUE ENCAJE PERFECTO
        return eventRepository.findById(id).map(existingEvent -> {
            existingEvent.setType(entity.getType());
            existingEvent.setStartDate(entity.getStartDate());
            existingEvent.setEndDate(entity.getEndDate());
            existingEvent.setDesc(entity.getDesc());
            return eventRepository.save(existingEvent);
        }).orElse(null);
    }
}