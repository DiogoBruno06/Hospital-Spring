package com.cadastro.hospital.repository;

import com.cadastro.hospital.entity.FarmaciaEntity;
import com.cadastro.hospital.enums.TipoRemedio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public interface FarmaciaRepository extends JpaRepository<FarmaciaEntity,Integer> {
    List<FarmaciaEntity> findByTipoRemedio(TipoRemedio tipoRemedio);
}
