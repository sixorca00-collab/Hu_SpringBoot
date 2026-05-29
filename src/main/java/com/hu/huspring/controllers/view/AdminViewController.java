package com.hu.huspring.controllers.view;

import com.hu.huspring.services.EventService;
import com.hu.huspring.services.CategoryService;
import com.hu.huspring.services.VenueService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminViewController {

    private final EventService eventService;
    private final VenueService venueService;
    private final CategoryService categoryService;

    public AdminViewController(EventService eventService, VenueService venueService, CategoryService categoryService) {
        this.eventService = eventService;
        this.venueService = venueService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String dashboard(Model model) {
        Pageable overviewPage = PageRequest.of(0, 5);
        model.addAttribute("eventCount", eventService.count());
        model.addAttribute("venueCount", venueService.count());
        model.addAttribute("categoryCount", categoryService.count());
        model.addAttribute("recentEvents", eventService.getAllPaginated(overviewPage).getContent());
        model.addAttribute("recentVenues", venueService.getAllPaginated(overviewPage).getContent());
        return "admin-dashboard";
    }
}
