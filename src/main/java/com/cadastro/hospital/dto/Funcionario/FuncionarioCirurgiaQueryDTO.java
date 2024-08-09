package com.cadastro.hospital.dto.Funcionario;

import com.cadastro.hospital.enums.TipoFuncionario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FuncionarioCirurgiaQueryDTO {
    private Integer idFuncionario;
    private String nome;
    private String email;
    private TipoFuncionario tipoFuncionario;
}
