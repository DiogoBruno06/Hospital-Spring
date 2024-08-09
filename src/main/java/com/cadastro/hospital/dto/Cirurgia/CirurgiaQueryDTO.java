package com.cadastro.hospital.dto.Cirurgia;

import com.cadastro.hospital.dto.Funcionario.FuncionarioCirurgiaQueryDTO;
import com.cadastro.hospital.dto.Paciente.PacienteCirurgiaQueryDTO;
import com.cadastro.hospital.enums.TipoCirurgia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CirurgiaQueryDTO {
    private Integer idCirurgia;
    private LocalDate dataCirurgia;
    private TipoCirurgia tipoCirurgia;
    private PacienteCirurgiaQueryDTO paciente;
    private List<FuncionarioCirurgiaQueryDTO> funcionarios;
}
