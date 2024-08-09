package com.cadastro.hospital.dto.Funcionario;

import com.cadastro.hospital.enums.Sexo;
import com.cadastro.hospital.enums.TipoFuncionario;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioCreateDTO {

    @NotNull
    @Size(min = 2,max = 255)
    @Schema(description = "Nome do Funcionario", required = true, example = "Elisangela Marques")
    private String nome;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    @PastOrPresent
    @Schema(description = "Uma data de nascimento", required = true, example = "1971-10-26")
    private LocalDate dataNascimento;


    @NotNull
    @Schema(description = "CPF de uma pessoa", required = true, example = "111111111-11")
    private String cpf;

    @NotNull
    @Schema(description = "Sexo do Paciente", required = true, example = "MASCULINO")
    private Sexo sexo;

    @NotBlank
    @Email
    @Size(min = 12)
    @Schema(description = "Email válido", required = true, example = "elisangela123@mail.com")
    private String email;


    @NotBlank
    @Size(min = 5, max = 30)
    @Schema(description = "Senha de acesso", required = true, example = "senha123")
    private String senha;

    private TipoFuncionario tipoFuncionario;
}
