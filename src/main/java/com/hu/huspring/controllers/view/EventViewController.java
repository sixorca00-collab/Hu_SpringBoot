package com.hu.huspring.controllers.view;

import com.hu.huspring.models.Event;
import com.hu.huspring.models.Venue;
import com.hu.huspring.services.EventService;
import com.hu.huspring.services.VenueService;
import org.springframework.data.domain.Pageable;
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

    public EventViewController(EventService Eservice, VenueService Vservice){
        this.Eservice = Eservice;
        this.Vservice = Vservice;
    }


    //Show creation form
    @GetMapping("/new")
    public String showCreationForm(Model model){

        Event event = new Event();

        event.setVenue(new Venue());

        model.addAttribute("event", event);

        model.addAttribute(
                "venues",
                Vservice.getAllPaginated(Pageable.unpaged()).getContent()
        );

        return "event-form";
    }

    @GetMapping
    public String eventList(Model model){
        model.addAttribute("events", Eservice.getAllPaginated(Pageable.unpaged())); // no las pagino ya que es una vista no un cliente como postman
        return "events-list";
    }

    @GetMapping("/edit/{id}")
    public String editEvent(@org.springframework.web.bind.annotation.PathVariable Long id, Model model) {
        return Eservice.getById(id).map(event -> {
            if (event.getVenue() == null) {
                event.setVenue(new Venue());
            }

            model.addAttribute("event", event);
            model.addAttribute("venues", Vservice.getAllPaginated(Pageable.unpaged()).getContent());
            return "event-form";
        }).orElse("redirect:/admin/events");
    }

    @PostMapping("/save")
    public String saveEvent(@ModelAttribute("event") Event event){
        attachVenue(event);
        Eservice.save(event);

        return "redirect:/admin/events";
    }

    @PostMapping("/update/{id}")
    public String updateEvent(@org.springframework.web.bind.annotation.PathVariable Long id,
                              @ModelAttribute("event") Event event) {
        attachVenue(event);
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

}
