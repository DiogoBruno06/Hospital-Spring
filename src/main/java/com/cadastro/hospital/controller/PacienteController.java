package com.cadastro.hospital.controller;

import com.cadastro.hospital.dto.Funcionario.FuncionarioCreateDTO;
import com.cadastro.hospital.dto.Paciente.PacienteCreateDTO;
import com.cadastro.hospital.dto.Paciente.PacienteDTO;
import com.cadastro.hospital.entity.FarmaciaEntity;
import com.cadastro.hospital.entity.PacienteEntity;
import com.cadastro.hospital.exception.RegraDeNegocioException;
import com.cadastro.hospital.service.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/PACIENTE")
public class PacienteController {
    private final PacienteService pacienteService;

    @PostMapping("/Cadastro-paciente")
    public ResponseEntity<PacienteDTO> LoginCreate(@RequestBody @Valid PacienteCreateDTO pacienteCreateDTO) throws RegraDeNegocioException {
        return new ResponseEntity<>(pacienteService.create(pacienteCreateDTO), HttpStatus.OK);
    }

    @GetMapping("/FindById")
    public PacienteEntity FindById(Integer idPaciente) throws RegraDeNegocioException {
        return pacienteService.findById(idPaciente);
    }

    @PutMapping("/Alta-Paciente/{idPaciente}")
    public ResponseEntity<PacienteDTO> AltaPaciente(@RequestParam @Valid Integer idPaciente) throws RegraDeNegocioException {
        return new ResponseEntity<>(pacienteService.altaPaciente(idPaciente), HttpStatus.OK);
    }

    @PutMapping("/Atendimento-Paciente/{idPaciente}")
    public ResponseEntity<PacienteDTO> AtendimentoPaciente(@RequestParam @Valid Integer idPaciente) throws RegraDeNegocioException {
        return new ResponseEntity<>(pacienteService.atendimentoPaciente(idPaciente), HttpStatus.OK);
    }
}
