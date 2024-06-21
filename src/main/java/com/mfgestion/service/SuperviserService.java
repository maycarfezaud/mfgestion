package com.mfgestion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfgestion.model.Superviser;
import com.mfgestion.repository.SuperviserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SuperviserService {

    @Autowired
    private SuperviserRepository superviserRepository;

    public Superviser saveSuperviser(Superviser superviser) {
        return superviserRepository.save(superviser);
    }

    public List<Superviser> getAllSupervisers() {
        return superviserRepository.findAll();
    }

    public Optional<Superviser> getSuperviserById(Long id) {
        return superviserRepository.findById(id);
    }

    public void deleteSuperviser(Long id) {
        superviserRepository.deleteById(id);
    }
}

