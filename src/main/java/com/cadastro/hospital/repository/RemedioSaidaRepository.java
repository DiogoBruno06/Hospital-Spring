package com.cadastro.hospital.repository;

import com.cadastro.hospital.entity.RemedioSaidaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RemedioSaidaRepository extends JpaRepository<RemedioSaidaEntity, Integer> {
}
