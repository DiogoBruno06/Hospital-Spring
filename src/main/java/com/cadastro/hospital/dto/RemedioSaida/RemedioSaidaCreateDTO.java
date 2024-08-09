package com.cadastro.hospital.dto.RemedioSaida;

import com.cadastro.hospital.enums.TipoRemedio;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RemedioSaidaCreateDTO {
    private String nomeRemedio;
    private Integer quantidade;
    private TipoRemedio tipoRemedio;
}
