package com.hu.huspring.controllers;

import com.hu.huspring.models.Event;
import com.hu.huspring.services.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService service;

    public EventController(EventService service) {
        this.service = service;
    }

    //Post
    @Tag(name = "Event-Controller", description = "Crud to events")
    @Operation(summary = "save event ", description = "endPoint save events in the database")
    @ApiResponse(responseCode = "201", description = "Event created success")
    @ApiResponse(responseCode = "401", description = "event has not created")
    @ApiResponse(responseCode = "500", description = "error in the database")
    @PostMapping
    public ResponseEntity<Event> save(@Parameter(description = "Object event", required = true) @RequestBody Event event) {

        Event savedEvent = service.save(event);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedEvent);
    }

    @Operation(summary = "get all events", description = "The list the events whitou filter")
    @ApiResponse(responseCode = "200", description = "Return the list with all events")
    @ApiResponse(responseCode = "404", description = "events not found")



    @GetMapping
    public Page<Event> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        org.springframework.data.domain.Sort sort = sortDir.equalsIgnoreCase("desc")
                ? org.springframework.data.domain.Sort.by(sortBy).descending()
                : org.springframework.data.domain.Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        return service.getAllPaginated(pageable);
    }
// search with id
    @Operation(summary = "Search event by id")
    @ApiResponse(responseCode = "200", description = "return the event with this id")
    @ApiResponse(responseCode = "404", description = "event not found")
    @GetMapping("/{id}")
    public ResponseEntity<Event> getById(@Parameter(description = "The id to search")@PathVariable Long id) {

        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @Operation(summary = "delete event", description = "delete event by Id")
    @ApiResponse(responseCode = "200",description = "The event has deleted success")
    @ApiResponse(responseCode = "404", description = "event not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@Parameter(description = "Id to delete", required = true)@PathVariable Long id) {

        boolean deleted = service.deleteById(id);

        if (deleted) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Update a event by id")
    @ApiResponse(responseCode = "200", description = "The event has update success")
    @ApiResponse(responseCode = "404", description = "event not found")
    @ApiResponse(responseCode = "401", description = "The event not has updated")
    @ApiResponse(responseCode = "500", description = "Error in database")
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