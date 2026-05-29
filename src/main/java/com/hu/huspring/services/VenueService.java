package com.hu.huspring.services;

import com.hu.huspring.models.Venue;
import com.hu.huspring.repositories.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VenueService {

    @Autowired
    private VenueRepository venueRepository;

    public Venue save(Venue venue) {
        return venueRepository.save(venue); // JPA se encarga del INSERT
    }

/*    public List<Venue> getAll() {
        return venueRepository.findAll(); // JPA hace el SELECT * FROM venues
    }*/

    public Page<Venue> getAllPaginated(Pageable pageable) {
        return venueRepository.findAll(pageable);
    }

    public long count() {
        return venueRepository.count();
    }

    public Optional<Venue> getById(Long id) {
        return venueRepository.findById(id);
    }

    public boolean deleteById(Long id) {
        if (venueRepository.existsById(id)) {
            venueRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Venue updateById(Long id, Venue entity) {
        return venueRepository.findById(id).map(existingVenue -> {
            existingVenue.setAddress(entity.getAddress());
            existingVenue.setFloor(entity.getFloor());
            existingVenue.setPrice(entity.getPrice());
            existingVenue.setCapacity(entity.getCapacity());
            return venueRepository.save(existingVenue); // JPA hace el UPDATE si el ID ya existe
        }).orElse(null);
    }
}
