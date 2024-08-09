package com.cadastro.hospital.dto.Cirurgia;


import com.cadastro.hospital.enums.TipoCirurgia;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CirurgiaCreateDTO {
    @NotNull(message = "Data da cirurgia não pode ser nula")
    @FutureOrPresent(message = "A data da cirurgia deve ser no presente ou no futuro")
    @Schema(description = "Data da cirurgia", required = true, example = "2024-04-28")
    private LocalDate dataCirurgia;

    @NotNull(message = "Tipo de cirurgia não pode ser nulo")
    @Schema(description = "Tipo de cirurgia", required = true)
    private TipoCirurgia tipoCirurgia;

    @NotEmpty(message = "A lista de funcionários da cirurgia não pode estar vazia")
    @Size(min = 1, message = "A lista de funcionários da cirurgia deve ter pelo menos um funcionário")
    @Schema(description = "Lista de IDs dos funcionários envolvidos na cirurgia", required = true)
    private List<Integer> funcionariosCirurgia;
}
