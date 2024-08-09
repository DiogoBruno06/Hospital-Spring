package com.cadastro.hospital.dto.Paciente;

import com.cadastro.hospital.enums.EntradaConsulta;
import com.cadastro.hospital.enums.Sexo;
import com.cadastro.hospital.enums.StatusPaciente;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class PacienteCreateDTO {
    @NotNull
    @Size(min = 2, max = 255)
    @Schema(description = "Nome do Paciente", required = true, example = "Diogo")
    private String nome;

    @NotNull
    @Size(min = 2, max = 255)
    @Schema(description = "Sobrenome do Paciente", required = true, example = "Bruno")
    private String sobrenome;

    @NotNull
    @Schema(description = "Sexo do Paciente", required = true, example = "MASCULINO")
    private Sexo sexo;

    @NotBlank
    @Email
    @Size(min = 12)
    @Schema(description = "Email válido", required = true, example = "diogo123@mail.com")
    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    @PastOrPresent
    @Schema(description = "Uma data de nascimento", required = true, example = "2004-10-06")
    private LocalDate dataNascimento;

    @NotNull
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF inválido")
    @Schema(description = "CPF de uma pessoa", required = true, example = "111.111.111-11")
    private String cpf;

    @NotNull
    @Schema(description = "Status de entrada para a consulta", required = true, example = "VERDE")
    private EntradaConsulta entradaConsulta;

    @JsonIgnore
    private StatusPaciente statusPaciente;
}
