package com.cadastro.hospital.dto.RemedioSaida;

import com.cadastro.hospital.entity.FuncionarioEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RemedioSaidaDTO extends RemedioSaidaCreateDTO{
    private Integer idRemedio;
    private Date saidaRemedio;
    private FuncionarioEntity funcionarioRemedio;
}
