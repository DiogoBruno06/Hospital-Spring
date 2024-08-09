package com.cadastro.hospital.controller;

import com.cadastro.hospital.dto.Cirurgia.CirurgiaCreateDTO;
import com.cadastro.hospital.dto.Cirurgia.CirurgiaDTO;

import com.cadastro.hospital.dto.Cirurgia.CirurgiaQueryDTO;
import com.cadastro.hospital.entity.CirurgiaEntity;
import com.cadastro.hospital.exception.EntidadeNaoEncontradaException;
import com.cadastro.hospital.exception.RegraDeNegocioException;
import com.cadastro.hospital.service.CirurgiaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/CIRURGIA")
@RequiredArgsConstructor
public class CirurgiaController {
    private final CirurgiaService cirurgiaService;

    @PostMapping("/Marcar-Cirurgia/{idPaciente}")
    public ResponseEntity<CirurgiaDTO> create2(@RequestBody CirurgiaCreateDTO cirurgiaCreateDTO, @PathVariable Integer idPaciente) throws EntidadeNaoEncontradaException {
        return new ResponseEntity<>(cirurgiaService.create2(cirurgiaCreateDTO,idPaciente), HttpStatus.CREATED);
    }

    @GetMapping("/FindById")
    public CirurgiaEntity FindById(Integer idCirurgia) throws RegraDeNegocioException {
        return cirurgiaService.findById(idCirurgia);
    }

    @GetMapping("/getCirurgiaDetailsById")
    public CirurgiaQueryDTO getCirurgiaDetailsById(Integer idCirurgia){
        return cirurgiaService.getCirurgiaDetailsById(idCirurgia);
    }
}
