package com.cadastro.hospital.dto.Funcionario;

import com.cadastro.hospital.enums.TipoFuncionario;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FuncionarioUpdateDTO {

    @Size(min = 2,max = 255)
    @Schema(description = "Nome do Funcionario", required = true, example = "Elisangela Marques")
    private String nome;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent
    @Schema(description = "Uma data de nascimento", required = true, example = "1971-10-26")
    private LocalDate dataNascimento;


    @Schema(description = "CPF de uma pessoa", required = true, example = "111111111-11")
    private String cpf;

    private TipoFuncionario tipoFuncionario;
}
