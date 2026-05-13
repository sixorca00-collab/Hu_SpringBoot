package com.hu.huspring.controllers;

import com.hu.huspring.models.Venue;
import com.hu.huspring.services.VenueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/venues")
public class VenueController {

    private final VenueService service;

    VenueController(VenueService service){
        this.service = service;
    }
    //Post
    @PostMapping
    public Venue save(@RequestBody Venue venue){
        service.save(venue);
        return venue;
    }

    //GET
    @GetMapping
    public List<Venue> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venue> getVenueById(@PathVariable Long id) {

        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Put
    @PutMapping("/{id}")
    public Venue updateVenue(@PathVariable Long id, @RequestBody Venue newVenue){
        service.updateById(id, newVenue);
        return newVenue;
    }

    // Delete
    @DeleteMapping("/{id}")
    public boolean deleteVenue(@PathVariable Long id){
        service.deleteById(id);
        return true;
    }

}
