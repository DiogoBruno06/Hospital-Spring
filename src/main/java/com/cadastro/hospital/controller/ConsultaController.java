package com.cadastro.hospital.controller;

import com.cadastro.hospital.dto.Consulta.ConsultaCreateDTO;
import com.cadastro.hospital.dto.Consulta.ConsultaDTO;
import com.cadastro.hospital.exception.EntidadeNaoEncontradaException;
import com.cadastro.hospital.service.ConsultaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Consulta")
public class ConsultaController {
    private final ConsultaService consultaService;

    @PostMapping("/cadastro-consulta")
    public ResponseEntity<ConsultaDTO> create(@RequestBody @Valid ConsultaCreateDTO consultaCreateDTO, Integer idPaciente) throws EntidadeNaoEncontradaException {
        return new ResponseEntity<>(consultaService.create(consultaCreateDTO,idPaciente), HttpStatus.OK);
    }

    @PutMapping("/editar-consulta")
    public ResponseEntity<ConsultaDTO> update(@RequestBody @Valid ConsultaCreateDTO consultaCreateDTO, Integer idConsulta,Integer idPaciente) throws EntidadeNaoEncontradaException{
        return new ResponseEntity<>(consultaService.update(idConsulta,idPaciente,consultaCreateDTO), HttpStatus.OK);
    }
}
