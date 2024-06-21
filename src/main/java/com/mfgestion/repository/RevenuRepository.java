package com.mfgestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mfgestion.model.Revenu;

@Repository
public interface RevenuRepository extends JpaRepository<Revenu, Long> {
}
