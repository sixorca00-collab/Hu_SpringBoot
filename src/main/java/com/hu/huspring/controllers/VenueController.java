package com.hu.huspring.controllers;

import com.hu.huspring.models.Venue;
import com.hu.huspring.services.VenueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Venue", description = "Info to he venues")

@RequestMapping("api/venues")
public class VenueController {

    private final VenueService service;

    VenueController(VenueService service){
        this.service = service;
    }
    //Post

    @Operation(summary = "Save the venues", description = "Function to add venues in the database")
    @ApiResponse(responseCode = "200", description = "venue save success")
    @ApiResponse(responseCode = "500", description = "Error in the database")
    @ApiResponse(responseCode = "401", description = "Not save venue")

    @PostMapping
    public Venue save(@Parameter(description = "Object Venue to create",
            required = true) @RequestBody Venue venue){
        service.save(venue);
        return venue;
    }

    //GET
    @Operation(summary = "Get all venues registered", description = "Function to search all venues in the database")
    @ApiResponse(responseCode = "200", description = "Return the list to venues")
    @ApiResponse(responseCode = "404", description = "Venues not found or registered")

    @GetMapping
    public List<Venue> getAll(){
        return service.getAll();
    }

    //Search by id
    @Operation(summary = "Search venue by id")
    @ApiResponse(responseCode = "200", description = "Return the venue with the id")
    @ApiResponse(responseCode = "404", description = "venue not found")
    @GetMapping("/{id}")
    public ResponseEntity<Venue> getVenueById(@Parameter(description = "The id to search", required = true )
                                                  @PathVariable Long id) {

        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Put
    @Operation(summary = "Update a venue by id")
    @ApiResponse(responseCode = "200", description = "The venue has update success")
    @ApiResponse(responseCode = "404", description = "venue not found")
    @ApiResponse(responseCode = "401", description = "The venue not has updated")
    @ApiResponse(responseCode = "500", description = "Error in database")
    @PutMapping("/{id}")
    public Venue updateVenue(@Parameter(description = "The id the venue to update", required = true)@PathVariable Long id, @RequestBody Venue newVenue){
        service.updateById(id, newVenue);
        return newVenue;
    }

    // Delete
    @Operation(summary = "delete venue", description = "delete venue by Id")
    @DeleteMapping("/{id}")
    public boolean deleteVenue(@Parameter(description = "Id  the venue to delete", required = true)@PathVariable Long id){
        service.deleteById(id);
        return true;
    }

}
