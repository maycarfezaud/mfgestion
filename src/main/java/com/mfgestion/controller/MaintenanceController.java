package com.mfgestion.controller;

import com.mfgestion.model.Maintenance;
import com.mfgestion.model.Vehicule;
import com.mfgestion.service.MaintenanceService;
import com.mfgestion.service.VehiculeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/maintenances")
public class MaintenanceController {

    @Autowired
    private MaintenanceService maintenanceService;

    @Autowired
    private VehiculeService vehiculeService;

    @ModelAttribute("vehicules")
    public List<Vehicule> initializeVehicules() {
        return vehiculeService.getAllVehicules();
    }

    @GetMapping("/index")
    public String getAllMaintenances(Model model) {
        List<Maintenance> maintenances = maintenanceService.getAllMaintenances();
        model.addAttribute("maintenances", maintenances);
        return "maintenances/index";
    }

    @GetMapping("/form")
    public String showMaintenanceForm(Model model) {
        Maintenance maintenance = new Maintenance();
        model.addAttribute("maintenance", maintenance);
        return "maintenances/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditMaintenanceForm(@PathVariable("id") Long id, Model model) {
        Optional<Maintenance> maintenanceOptional = maintenanceService.getMaintenanceById(id);
        if (maintenanceOptional.isPresent()) {
            model.addAttribute("maintenance", maintenanceOptional.get());
            return "maintenances/form";
        } else {
            return "redirect:/maintenances/index";
        }
    }

    @PostMapping("/save")
    public String saveMaintenance(@ModelAttribute("maintenance") Maintenance maintenance, @RequestParam("vehicule.id") Long vehiculeId) {
        Vehicule vehicule = vehiculeService.getVehiculeById(vehiculeId).orElse(null);
        maintenance.setVehicule(vehicule);
        maintenanceService.saveMaintenance(maintenance);
        return "redirect:/maintenances/index";
    }



    @GetMapping("/delete/{id}")
    public String deleteMaintenance(@PathVariable("id") Long id) {
        maintenanceService.deleteMaintenance(id);
        return "redirect:/maintenances/index";
    }
}
