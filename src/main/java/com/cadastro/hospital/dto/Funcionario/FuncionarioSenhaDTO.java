package com.cadastro.hospital.dto.Funcionario;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class FuncionarioSenhaDTO {
    @NotNull
    @NotBlank
    private String senha;
}
