package com.cadastro.hospital.repository;

import com.cadastro.hospital.entity.FuncionarioEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity,Integer> {

    Optional<FuncionarioEntity> findByLogin(String login);
    boolean existsByEmail(String email);
}
