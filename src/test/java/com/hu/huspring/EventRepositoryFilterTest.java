package com.hu.huspring;

import com.hu.huspring.dtos.EventDTO;
import com.hu.huspring.repositories.EventRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class EventRepositoryFilterTest {

    @Autowired
    private EventRepository eventRepository;

    @Test
    void shouldReturnEventsOrderedByStartDateDescending() {
        List<EventDTO> events = eventRepository.findByFilters(null, null, PageRequest.of(0, 5)).getContent();

        assertThat(events).hasSize(5);
        assertThat(events.get(0).startDate()).isAfterOrEqualTo(events.get(1).startDate());
    }

    @Test
    void shouldFindRockEventByPartialCategoryAndCity() {
        List<EventDTO> events = eventRepository.findByFilters("rock", "bog", PageRequest.of(0, 20)).getContent();

        assertThat(events).isNotEmpty();
        assertThat(events).anySatisfy(event -> {
            assertThat(event.desc()).isEqualTo("Concierto de ROCK");
            assertThat(event.categoryName()).isEqualToIgnoringCase("ROCK");
            assertThat(event.venueCity()).containsIgnoringCase("bog");
        });
    }
}
