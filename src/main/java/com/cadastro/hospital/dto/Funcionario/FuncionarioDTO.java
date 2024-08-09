package com.cadastro.hospital.dto.Funcionario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FuncionarioDTO extends FuncionarioCreateDTO{
    private Integer idFuncionario;
}
