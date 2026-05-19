package com.hu.huspring;

import com.hu.huspring.models.Event;
import com.hu.huspring.repositories.EventRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest // Configura una BD en memoria aislada para pruebas
public class EventRepositoryTest {

    @Autowired
    private EventRepository eventRepository;

    @Test
    public void deberiaGuardarUnEventoExitosamente() {
        // Given
        Event event = new Event();
        event.setDesc("Prueba de integración");

        // When
        Event guardado = eventRepository.save(event);

        // Then
        assertThat(guardado).isNotNull();
        assertThat(guardado.getId()).isGreaterThan(0);
    }
}