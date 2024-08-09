package com.cadastro.hospital.repository;

import com.cadastro.hospital.entity.CirurgiaEntity;
import com.cadastro.hospital.enums.TipoFuncionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CirurgiaRepository extends JpaRepository<CirurgiaEntity, Integer> {

}


