package com.aep.inovalocal.repository;

import com.aep.inovalocal.model.Deslocamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeslocamentoRepository extends JpaRepository<Deslocamento, Long> {
}
