package com.hu.huspring;

import com.hu.huspring.controllers.view.EventViewController;
import com.hu.huspring.services.EventService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EventViewController.class) // Pruebas focalizadas en el controlador de vista
public class EventViewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventService eventService; // Simulamos el servicio para aislar la vista

    @MockBean
    private com.hu.huspring.services.VenueService venueService; // También simulamos el de sedes

    @Test
    public void testListEventsViewShouldReturnSuccessAndModel() throws Exception {
        mockMvc.perform(get("/admin/events")
                        .contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk()) // Valida estado 200 OK
                .andExpect(view().name("event-list")) // Valida que busque 'event-list.html'
                .andExpect(model().attributeExists("events")); // Valida que el objeto 'events' vaya en el modelo
    }
}