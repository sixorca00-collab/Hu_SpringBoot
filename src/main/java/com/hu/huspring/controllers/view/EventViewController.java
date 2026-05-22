package com.hu.huspring.controllers.view;

import com.hu.huspring.models.Event;
import com.hu.huspring.models.Venue;
import com.hu.huspring.services.EventService;
import com.hu.huspring.services.VenueService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/events")
public class EventViewController {
    private EventService Eservice;
    private VenueService Vservice;

    public EventViewController(EventService Eservice, VenueService Vservice){
        this.Eservice = Eservice;
        this.Vservice = Vservice;
    }


    //Sow creation form
    @GetMapping("/new")
    public String showCreationForm(Model model){
        model.addAttribute("event", new Event());
        model.addAttribute("venue", new Venue());
        return "event-form";
    }

}
