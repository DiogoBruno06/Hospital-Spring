package com.cadastro.hospital.service;

import com.cadastro.hospital.dto.RemedioSaida.RemedioSaidaDTO;
import com.cadastro.hospital.repository.RemedioSaidaRepository;
import com.cadastro.hospital.util.ConversorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RemedioSaidaService {
    private final RemedioSaidaRepository remedioSaidaRepository;

    public List<RemedioSaidaDTO> findAll() {
        return remedioSaidaRepository.findAll().stream()
                .map(remedioSaidaEntity -> ConversorMapper.converter(remedioSaidaEntity, RemedioSaidaDTO.class))
                .collect(Collectors.toList());
    }
}
