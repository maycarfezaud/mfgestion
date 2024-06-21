package com.mfgestion.controller;

import com.mfgestion.model.Revenu;
import com.mfgestion.model.User;
import com.mfgestion.model.Vehicule;
import com.mfgestion.service.RevenuService;
import com.mfgestion.service.UserService;
import com.mfgestion.service.VehiculeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/revenus")
public class RevenuController {

    @Autowired
    private RevenuService revenuService;

    @Autowired
    private UserService userService;

    @Autowired
    private VehiculeService vehiculeService;

    @GetMapping("/index")
    public String getAllRevenus(Model model) {
        List<Revenu> revenus = revenuService.getAllRevenus();
        model.addAttribute("revenus", revenus);
        return "revenus/index"; // Le nom du fichier Thymeleaf dans templates/revenu/
    }

    @GetMapping("/form")
    public String showRevenuForm(Model model) {
        Revenu revenu = new Revenu();
        List<User> chauffeurs = userService.getAllUsers().stream()
                                          .filter(user -> user.getRole().equals(User.Role.CHAUFFEUR))
                                          .collect(Collectors.toList());
        List<Vehicule> vehicules = vehiculeService.getAllVehicules();
        model.addAttribute("revenu", revenu);
        model.addAttribute("chauffeurs", chauffeurs);
        model.addAttribute("vehicules", vehicules);
        return "revenus/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditRevenuForm(@PathVariable("id") Long id, Model model) {
        Optional<Revenu> revenuOptional = revenuService.getRevenuById(id);
        if (revenuOptional.isPresent()) {
            List<User> chauffeurs = userService.getAllUsers().stream()
                                               .filter(user -> user.getRole().equals(User.Role.CHAUFFEUR))
                                               .collect(Collectors.toList());
            List<Vehicule> vehicules = vehiculeService.getAllVehicules();
            model.addAttribute("revenu", revenuOptional.get());
            model.addAttribute("chauffeurs", chauffeurs);
            model.addAttribute("vehicules", vehicules);
            return "revenus/form";
        } else {
            return "redirect:/revenus/index";
        }
    }

    @PostMapping("/save")
    public String saveRevenu(@ModelAttribute("revenu") Revenu revenu) {
        revenuService.saveRevenu(revenu);
        return "redirect:/revenus/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteRevenu(@PathVariable("id") Long id) {
        revenuService.deleteRevenu(id);
        return "redirect:/revenus/index";
    }
}
