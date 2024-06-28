package com.mfgestion.controller;

import com.mfgestion.model.Feedback;
import com.mfgestion.model.User;
import com.mfgestion.model.Vehicule;
import com.mfgestion.service.FeedbackService;
import com.mfgestion.service.UserService;
import com.mfgestion.service.VehiculeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private UserService userService;

    @Autowired
    private VehiculeService vehiculeService;

    @GetMapping("/home")
    public String home(Model model, Authentication authentication) {
        if (authentication != null) {
            model.addAttribute("role", authentication.getAuthorities().iterator().next().getAuthority());
        }
        return "home";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/news")
    public String news() {
        return "news";
    }

    @GetMapping("/feedback")
    public String showFeedbackPage(Model model) {
        List<Feedback> feedbacks = feedbackService.getAllFeedbacks();
        List<User> chauffeurs = userService.findByRole(User.Role.CHAUFFEUR);
        List<User> passagers = userService.findByRole(User.Role.PASSAGER);
        List<Vehicule> vehicules = vehiculeService.getAllVehicules();

        model.addAttribute("feedbacks", feedbacks);
        model.addAttribute("feedback", new Feedback());
        model.addAttribute("chauffeurs", chauffeurs);
        model.addAttribute("passagers", passagers);
        model.addAttribute("vehicules", vehicules);

        return "feedback";
    }

    @PostMapping("/feedback")
    public String addFeedback(@Valid @ModelAttribute("feedback") Feedback feedback, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Feedback> feedbacks = feedbackService.getAllFeedbacks();
            model.addAttribute("feedbacks", feedbacks);
            return "feedback";
        }
        feedbackService.saveFeedback(feedback);
        return "redirect:/feedback";
    }
}
