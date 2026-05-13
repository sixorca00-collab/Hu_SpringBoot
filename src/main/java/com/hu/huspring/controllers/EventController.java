package com.hu.huspring.controllers;

import com.hu.huspring.models.Event;
import com.hu.huspring.services.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService service;

    public EventController(EventService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Event> save(@RequestBody Event event) {

        Event savedEvent = service.save(event);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedEvent);
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAll() {

        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getById(@PathVariable Long id) {

        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {

        boolean deleted = service.deleteById(id);

        if (deleted) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> update(
            @PathVariable Long id,
            @RequestBody Event event
    ) {

        Event updatedEvent = service.updateById(id, event);

        if (updatedEvent == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedEvent);
    }
}