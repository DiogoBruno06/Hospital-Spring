package com.cadastro.hospital.repository;

import com.cadastro.hospital.entity.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<PacienteEntity,Integer> {
    boolean existsByCpf(String cpf);
}
