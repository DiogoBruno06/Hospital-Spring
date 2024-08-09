package com.cadastro.hospital.controller;


import com.cadastro.hospital.dto.Funcionario.FuncionarioDTO;
import com.cadastro.hospital.dto.Funcionario.FuncionarioUpdateDTO;
import com.cadastro.hospital.service.FuncionarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/funcionario")
@RequiredArgsConstructor
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @PutMapping("editar-funcionario")
    ResponseEntity<FuncionarioDTO> updateFuncionario(@Valid FuncionarioUpdateDTO funcionarioUpdateDTO) throws Exception {
        return new ResponseEntity<>(funcionarioService.update(funcionarioUpdateDTO),HttpStatus.OK);
    }
}
