package com.mfgestion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.mfgestion.model.Superviser;
import com.mfgestion.service.SuperviserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/supervisers")
public class SuperviserController {

    @Autowired
    private SuperviserService superviserService;

    @PostMapping("/create")
    public Superviser createSuperviser(@RequestBody Superviser superviser) {
        return superviserService.saveSuperviser(superviser);
    }

    @GetMapping("/all")
    public List<Superviser> getAllSupervisers() {
        return superviserService.getAllSupervisers();
    }

    @GetMapping("/{id}")
    public Optional<Superviser> getSuperviserById(@PathVariable Long id) {
        return superviserService.getSuperviserById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSuperviser(@PathVariable Long id) {
        superviserService.deleteSuperviser(id);
    }
}
