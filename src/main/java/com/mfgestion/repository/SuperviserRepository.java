package com.mfgestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mfgestion.model.Superviser;

@Repository
public interface SuperviserRepository extends JpaRepository<Superviser, Long> {
}
