package com.cadastro.hospital.controller;

import com.cadastro.hospital.dto.Funcionario.FuncionarioCreateDTO;
import com.cadastro.hospital.dto.Funcionario.LoginDTO;
import com.cadastro.hospital.entity.FuncionarioEntity;
import com.cadastro.hospital.exception.RegraDeNegocioException;
import com.cadastro.hospital.security.TokenService;
import com.cadastro.hospital.service.FuncionarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@Validated
@RequiredArgsConstructor
public class AuthController {
    public final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final FuncionarioService funcionarioService;


    @PostMapping
    public String auth(@RequestBody @Valid LoginDTO loginDTO) throws RegraDeNegocioException {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getLogin(),
                        loginDTO.getSenha()
                );

        Authentication authentication =
                authenticationManager.authenticate(
                        usernamePasswordAuthenticationToken);

        FuncionarioEntity funcionarioValidado = (FuncionarioEntity) authentication.getPrincipal();

        return tokenService.generateToken(funcionarioValidado);
    }

    @PostMapping("/cadastro")
    public ResponseEntity<FuncionarioCreateDTO> loginCreate(@RequestBody @Valid FuncionarioCreateDTO loginCreateDTO) throws RegraDeNegocioException {
        return new ResponseEntity<>(funcionarioService.create(loginCreateDTO), HttpStatus.OK);
    }
}
