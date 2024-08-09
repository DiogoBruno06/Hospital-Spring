package com.cadastro.hospital.dto.Paciente;

import com.cadastro.hospital.enums.StatusPaciente;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PacienteDTO extends PacienteCreateDTO{
    private Integer idPaciente;

    private StatusPaciente statusPaciente;
}
