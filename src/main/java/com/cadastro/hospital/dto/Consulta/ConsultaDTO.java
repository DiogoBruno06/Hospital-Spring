package com.cadastro.hospital.dto.Consulta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConsultaDTO extends ConsultaCreateDTO{
    private Integer idConsulta;
}
