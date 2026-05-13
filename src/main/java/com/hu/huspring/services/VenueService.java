package com.hu.huspring.services;

import com.hu.huspring.models.Venue;
import com.hu.huspring.repositories.CrudRepository;
import com.hu.huspring.repositories.VenueRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VenueService implements CrudRepository<Venue, Long> {

    private final VenueRepository repository;

    public VenueService(VenueRepository repository){
        this.repository = repository;
    }


    @Override
    public Venue save(Venue venue) {
        repository.save(venue);
        return venue;
    }

    @Override
    public List<Venue> getAll() {
        return repository.getAll();
    }

    @Override
    public Optional<Venue> getById(Long id) {
        return repository.getById(id);
    }

    @Override
    public boolean deleteById(Long id) {
        return repository.deleteById(id);
    }

    @Override
    public Venue updateById(Long id, Venue newVanue) {
        return repository.updateById(id, newVanue);
    }
}
