package com.hu.huspring.repositories;

import com.hu.huspring.models.Venue;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class VenueRepository implements CrudRepository<Venue, Long> {

    private final List<Venue> venues = new ArrayList<>();

    @Override
    public Venue save(Venue venue) {
        venues.add(venue);
        return venue;
    }

    @Override
    public List<Venue> getAll() {
        return venues;
    }

    @Override
    public Optional<Venue> getById(Long id) {
        return venues.stream().filter(v -> v.getId().equals(id)).findFirst();
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<Venue> search = getById(id);
        if (search.isEmpty()){
            System.out.println("The venue not exist");
            return false;
        }
        Venue venue = search.get();
        venues.remove(venue);
        return true;
    }

    @Override
    public Venue updateById(Long id, Venue newVenue) {

        Optional<Venue> search = getById(id);

        if (search.isEmpty()) {
            System.out.println("The venue does not exist");
            return null;
        }

        Venue venueFound = search.get();
        venueFound.setFloor(newVenue.getFloor());
        venueFound.setAddress(newVenue.getAddress());
        venueFound.setCapacity(newVenue.getCapacity());
        venueFound.setPrice(newVenue.getPrice());
        return venueFound;
    }
}
