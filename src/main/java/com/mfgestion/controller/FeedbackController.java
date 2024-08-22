package com.mfgestion.controller;

import com.mfgestion.model.Feedback;
import com.mfgestion.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/index")
    public String getAllFeedbacks(Model model) {
        List<Feedback> feedbacks = feedbackService.getAllFeedbacks();
        model.addAttribute("feedbacks", feedbacks);
        return "feedbacks/index"; 
    }

    @GetMapping("/form")
    public String showFeedbackForm(Model model) {
        Feedback feedback = new Feedback();
        model.addAttribute("feedback", feedback);
        return "feedbacks/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditFeedbackForm(@PathVariable("id") Long id, Model model) {
        Optional<Feedback> feedbackOptional = feedbackService.getFeedbackById(id);
        if (feedbackOptional.isPresent()) {
            model.addAttribute("feedback", feedbackOptional.get());
            return "feedbacks/form";
        } else {
            return "redirect:/feedbacks/index";
        }
    }

    @PostMapping("/save")
    public String saveFeedback(@Valid @ModelAttribute("feedback") Feedback feedback, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("feedback", feedback);
            return "feedbacks/form"; // Retourner au formulaire en cas d'erreurs de validation
        }
        feedbackService.saveFeedback(feedback);
        return "redirect:/feedbacks/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteFeedback(@PathVariable("id") Long id) {
        feedbackService.deleteFeedback(id);
        return "redirect:/feedbacks/index";
    }
}
