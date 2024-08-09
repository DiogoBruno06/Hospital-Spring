package com.cadastro.hospital.dto.Cirurgia;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CirurgiaDTO extends CirurgiaCreateDTO{
    private Integer idCirurgia;
}
