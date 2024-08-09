package com.cadastro.hospital.controller;

import com.cadastro.hospital.dto.RemedioSaida.RemedioSaidaDTO;
import com.cadastro.hospital.service.RemedioSaidaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/REMEDIO-SAIDA")
@RequiredArgsConstructor
public class RemedioSaidaController {
    private final RemedioSaidaService remedioSaidaService;
    @GetMapping
    public List<RemedioSaidaDTO> findAll() {
        return remedioSaidaService.findAll();
    }
}
