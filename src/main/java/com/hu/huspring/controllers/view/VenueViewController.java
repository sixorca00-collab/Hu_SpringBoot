package com.hu.huspring.controllers.view;

import com.hu.huspring.models.Venue;
import com.hu.huspring.services.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/venues") // Ruta web separada para Sedes
public class VenueViewController {
    @Autowired
    private VenueService venueService;

    // 1. LISTADO DE SEDES
    @GetMapping
    public String listVenues(Model model) {
        model.addAttribute("venues", venueService.getAllPaginated(Pageable.unpaged()).getContent());
        return "venue-list"; // Busca venue-list.html
    }

    // 2. FORMULARIO PARA NUEVA SEDE
    @GetMapping("/new")
    public String showVenueForm(Model model) {
        model.addAttribute("venue", new Venue());
        return "venue-form"; // Busca venue-form.html
    }

    // 3. GUARDAR SEDE (POST-REDIRECT-GET)
    @PostMapping("/save")
    public String saveVenue(@ModelAttribute("venue") Venue venue) {
        venueService.save(venue); // Guarda en tu H2 local
        return "redirect:/admin/venues"; // Redirige al listado de sedes
    }
}