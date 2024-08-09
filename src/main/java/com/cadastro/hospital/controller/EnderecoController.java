package com.cadastro.hospital.controller;

import com.cadastro.hospital.dto.Endereco.EnderecoCreateDTO;
import com.cadastro.hospital.dto.Endereco.EnderecoDTO;
import com.cadastro.hospital.exception.EntidadeNaoEncontradaException;
import com.cadastro.hospital.service.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/endereco")
@RequiredArgsConstructor
public class EnderecoController {
    private final EnderecoService enderecoService;

    @GetMapping
    public List<EnderecoDTO> findAll() {
        return enderecoService.findAll();
    }

    @GetMapping("/{idEndereco}")
    public ResponseEntity<EnderecoDTO> findById(@PathVariable("idEndereco") @Positive(message = "O formato do id não é válido") Integer idEndereco) throws Exception {
        return new ResponseEntity<>(enderecoService.findById(idEndereco), HttpStatus.OK);
    }

    @PostMapping("/{idFuncionario}")
    public ResponseEntity<EnderecoDTO> save(@PathVariable("idFuncionario") @Positive(message = "O formato do id não é válido") Integer idFuncionario,
                                            @RequestBody @Valid EnderecoCreateDTO endereco) throws Exception {
        return new ResponseEntity<>(enderecoService.save(idFuncionario, endereco), HttpStatus.ACCEPTED);
    }

    @PutMapping("/{idEndereco}")
    public ResponseEntity<EnderecoDTO> update(@PathVariable("idEndereco") @Positive(message = "O formato do id não é válido") Integer idEndereco,
                                              @RequestBody @Valid EnderecoCreateDTO enderecoEntity) throws Exception {
        return new ResponseEntity<>(enderecoService.update(idEndereco, enderecoEntity), HttpStatus.OK);
    }

    @DeleteMapping("/{idEndereco}")
    public ResponseEntity<Void> delete(@PathVariable("idEndereco") @Positive(message = "O formato do id não é válido") Integer idEndereco) throws EntidadeNaoEncontradaException {
        enderecoService.delete(idEndereco);
        return ResponseEntity.noContent().build();
    }
}
