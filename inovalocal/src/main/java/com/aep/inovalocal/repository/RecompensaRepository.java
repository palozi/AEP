package com.aep.inovalocal.repository;

import com.aep.inovalocal.model.Recompensa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecompensaRepository  extends JpaRepository <Recompensa, Long> {
}
