package com.cadastro.hospital.dto.Paciente;

import com.cadastro.hospital.enums.StatusPaciente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PacienteCirurgiaQueryDTO {
    private Integer idPaciente;
    private String nome;
    private String sobrenome;
    private String email;
}
