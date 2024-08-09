package com.cadastro.hospital.dto.Consulta;

import com.cadastro.hospital.enums.TipoConsulta;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConsultaCreateDTO {
    @NotNull
    @Schema(description = "Tipo da Consulta", required = true, example = "GASTRO")
    private TipoConsulta tipoConsulta;

    @NotNull
    @Schema(description = "Data do atendimento", required = true, example = "2023-10-06")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent
    private LocalDate dataConsulta;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9\\s.,;:!?()-]+$", message = "Sintomas inválidos")
    @Size(min = 3, max = 255)
    @Schema(description = "Sintomas do paciente", required = true, example = "Febre")
    private String sintomas;

    @NotNull
    @Size(min = 1, max = 1000)
    @Schema(description = "Observações adicionais", required = true, example = "Paciente apresentou dor abdominal severa.")
    private String observacoes;

}
