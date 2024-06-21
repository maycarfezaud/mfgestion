package com.mfgestion.controller;

import com.mfgestion.model.Vehicule;
import com.mfgestion.service.VehiculeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/vehicules")
public class VehiculeController {

    @Autowired
    private VehiculeService vehiculeService;

    @GetMapping("/index")
    public String getAllVehicules(Model model) {
        List<Vehicule> vehicules = vehiculeService.getAllVehicules();
        model.addAttribute("vehicules", vehicules);
        return "vehicules/index";
    }

    @GetMapping("/form")
    public String showVehiculeForm(Model model) {
        Vehicule vehicule = new Vehicule();
        model.addAttribute("vehicule", vehicule);
        return "vehicules/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditVehiculeForm(@PathVariable("id") Long id, Model model) {
        Optional<Vehicule> vehiculeOptional = vehiculeService.getVehiculeById(id);
        if (vehiculeOptional.isPresent()) {
            model.addAttribute("vehicule", vehiculeOptional.get());
            return "vehicules/form";
        } else {
            return "redirect:/vehicules/index";
        }
    }

    @PostMapping("/save")
    public String saveVehicule(@ModelAttribute("vehicule") Vehicule vehicule) {
        vehiculeService.saveVehicule(vehicule);
        return "redirect:/vehicules/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteVehicule(@PathVariable("id") Long id) {
        vehiculeService.deleteVehicule(id);
        return "redirect:/vehicules/index";
    }
}
