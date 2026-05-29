package com.hu.huspring;

import com.hu.huspring.controllers.view.EventViewController;
import com.hu.huspring.dtos.EventDTO;
import com.hu.huspring.services.EventService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
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

    @MockBean
    private com.hu.huspring.services.CategoryService categoryService;

    @Test
    public void testListEventsViewShouldReturnSuccessAndModel() throws Exception {
        Slice<EventDTO> events = new SliceImpl<>(List.of());
        when(eventService.getAllPaginated(PageRequest.of(0, 10))).thenReturn(events);

        mockMvc.perform(get("/admin/events")
                        .contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk()) // Valida estado 200 OK
                .andExpect(view().name("events-list")) // Valida que busque 'events-list.html'
                .andExpect(model().attributeExists("events")); // Valida que el objeto 'events' vaya en el modelo
    }
}
