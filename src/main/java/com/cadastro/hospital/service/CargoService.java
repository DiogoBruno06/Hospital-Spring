package com.cadastro.hospital.service;

import com.cadastro.hospital.entity.CargoEntity;
import com.cadastro.hospital.exception.RegraDeNegocioException;
import com.cadastro.hospital.repository.CargoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class CargoService {

    private final CargoRepository cargoRepository;

    public CargoEntity getByid(Integer idCargo) throws RegraDeNegocioException {
        return findByid(idCargo);
    }
    public CargoEntity findByid(Integer idCargo) throws RegraDeNegocioException {
        return cargoRepository.findById(idCargo).orElseThrow(()-> new RegraDeNegocioException("Cargo nao encontrado"));
    }
}
