package com.cadastro.hospital.dto.Farmacia;

import com.cadastro.hospital.enums.StatusFarmacia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FarmaciaDTO extends FarmaciaCreateDTO{
    private Integer idFarmacia;
    private LocalDate dataPedido;
    private LocalDate dataEntrega;
    private StatusFarmacia statusFarmacia;
}
