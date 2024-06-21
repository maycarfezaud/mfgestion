package com.mfgestion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfgestion.model.Revenu;
import com.mfgestion.repository.RevenuRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RevenuService {

    @Autowired
    private RevenuRepository revenuRepository;

    public Revenu saveRevenu(Revenu revenu) {
        return revenuRepository.save(revenu);
    }

    public List<Revenu> getAllRevenus() {
        return revenuRepository.findAll();
    }

    public Optional<Revenu> getRevenuById(Long id) {
        return revenuRepository.findById(id);
    }

    public void deleteRevenu(Long id) {
        revenuRepository.deleteById(id);
    }
}

