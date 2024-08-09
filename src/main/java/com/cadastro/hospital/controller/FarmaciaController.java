package com.cadastro.hospital.controller;

import com.cadastro.hospital.dto.Consulta.ConsultaCreateDTO;
import com.cadastro.hospital.dto.Consulta.ConsultaDTO;
import com.cadastro.hospital.dto.Endereco.EnderecoDTO;
import com.cadastro.hospital.dto.Farmacia.FarmaciaCreateDTO;
import com.cadastro.hospital.dto.Farmacia.FarmaciaDTO;
import com.cadastro.hospital.entity.FarmaciaEntity;
import com.cadastro.hospital.exception.EntidadeNaoEncontradaException;
import com.cadastro.hospital.exception.RegraDeNegocioException;
import com.cadastro.hospital.service.FarmaciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/FARMACIA")
public class FarmaciaController {
    private final FarmaciaService farmaciaService;

    @PostMapping("/Pedido-Farmacia")
    public ResponseEntity<FarmaciaDTO> create(@RequestBody @Valid FarmaciaCreateDTO farmaciaCreateDTO) throws EntidadeNaoEncontradaException {
        return new ResponseEntity<>(farmaciaService.create(farmaciaCreateDTO), HttpStatus.OK);
    }

    @PutMapping("/Entrega-Pedido")
    public ResponseEntity<FarmaciaDTO> entregaPedido(Integer idFarmacia) throws EntidadeNaoEncontradaException {
        return new ResponseEntity<>(farmaciaService.entregaRemedio(idFarmacia), HttpStatus.OK);
    }

    @PutMapping("/Retirada-Pedido")
    public ResponseEntity<FarmaciaDTO> retiradaRemedio(Integer idFarmacia, Integer quantidade) throws EntidadeNaoEncontradaException, Exception {
        return new ResponseEntity<>(farmaciaService.retiradaRemedio(idFarmacia,quantidade), HttpStatus.OK);
    }

    @GetMapping("/FindById")
    public FarmaciaEntity findAll(Integer idFarmacia) throws RegraDeNegocioException {
        return farmaciaService.findById(idFarmacia);
    }

    @GetMapping("/Injetaveis")
    public List<FarmaciaEntity> findInjetavel(){
        return farmaciaService.queryInjetavel();
    }

    @GetMapping("/Comprimidos")
    public List<FarmaciaEntity> findComprimido(){
        return farmaciaService.queryComprimido();
    }

    @GetMapping("/Liquido")
    public List<FarmaciaEntity> findLiquido(){
        return farmaciaService.queryLiquido();
    }
}
