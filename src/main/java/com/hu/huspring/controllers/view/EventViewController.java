package com.hu.huspring.controllers.view;

import com.hu.huspring.models.Event;
import com.hu.huspring.models.Category;
import com.hu.huspring.models.Venue;
import com.hu.huspring.services.CategoryService;
import com.hu.huspring.services.EventService;
import com.hu.huspring.services.VenueService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/events")
public class EventViewController {
    private final EventService Eservice;
    private final VenueService Vservice;
    private final CategoryService Cservice;

    public EventViewController(EventService Eservice, VenueService Vservice, CategoryService Cservice){
        this.Eservice = Eservice;
        this.Vservice = Vservice;
        this.Cservice = Cservice;
    }


    //Show creation form
    @GetMapping("/new")
    public String showCreationForm(Model model){

        Event event = new Event();

        event.setVenue(new Venue());
        event.setCategory(new Category());

        model.addAttribute("event", event);

        model.addAttribute(
                "venues",
                Vservice.getAllPaginated(Pageable.unpaged()).getContent()
        );
        model.addAttribute("categories", Cservice.getAll());

        return "event-form";
    }

    @GetMapping
    public String eventList(
            @org.springframework.web.bind.annotation.RequestParam(defaultValue = "0") int page,
            @org.springframework.web.bind.annotation.RequestParam(defaultValue = "10") int size,
            @org.springframework.web.bind.annotation.RequestParam(required = false) String category,
            @org.springframework.web.bind.annotation.RequestParam(required = false) String city,
            Model model){
        Pageable pageable = PageRequest.of(page, size);
        boolean hasFilters = (category != null && !category.isBlank()) || (city != null && !city.isBlank());
        model.addAttribute("events", hasFilters
                ? Eservice.getAllPaginated(pageable, category, city)
                : Eservice.getAllPaginated(pageable));
        model.addAttribute("categoryFilter", category == null ? "" : category);
        model.addAttribute("cityFilter", city == null ? "" : city);
        model.addAttribute("pageSize", size);
        return "events-list";
    }

    @GetMapping("/edit/{id}")
    public String editEvent(@org.springframework.web.bind.annotation.PathVariable Long id, Model model) {
        return Eservice.getById(id).map(event -> {
            if (event.getVenue() == null) {
                event.setVenue(new Venue());
            }
            if (event.getCategory() == null) {
                event.setCategory(new Category());
            }

            model.addAttribute("event", event);
            model.addAttribute("venues", Vservice.getAllPaginated(Pageable.unpaged()).getContent());
            model.addAttribute("categories", Cservice.getAll());
            return "event-form";
        }).orElse("redirect:/admin/events");
    }

    @PostMapping("/save")
    public String saveEvent(@ModelAttribute("event") Event event){
        attachVenue(event);
        attachCategory(event);
        Eservice.save(event);

        return "redirect:/admin/events";
    }

    @PostMapping("/update/{id}")
    public String updateEvent(@org.springframework.web.bind.annotation.PathVariable Long id,
                              @ModelAttribute("event") Event event) {
        attachVenue(event);
        attachCategory(event);
        Eservice.updateById(id, event);
        return "redirect:/admin/events";
    }

    @PostMapping("/delete/{id}")
    public String deleteEvent(@org.springframework.web.bind.annotation.PathVariable Long id) {
        Eservice.deleteById(id);
        return "redirect:/admin/events";
    }

    private void attachVenue(Event event) {
        if (event.getVenue() == null || event.getVenue().getId() == null) {
            event.setVenue(null);
            return;
        }

        Vservice.getById(event.getVenue().getId())
                .ifPresentOrElse(event::setVenue, () -> event.setVenue(null));
    }

    private void attachCategory(Event event) {
        if (event.getCategory() == null || event.getCategory().getId() == null) {
            event.setCategory(null);
            return;
        }

        Cservice.getById(event.getCategory().getId())
                .ifPresentOrElse(event::setCategory, () -> event.setCategory(null));
    }

}
