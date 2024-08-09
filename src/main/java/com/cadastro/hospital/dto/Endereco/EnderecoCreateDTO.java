package com.cadastro.hospital.dto.Endereco;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoCreateDTO {

    private String apelido;

    @NotNull(message = "Logradouro não pode ser vazio")
    @NotBlank
    @Size(min = 3, max = 254, message = "Logradouro deve ter no mínimo 3 letras")
    private String logradouro;

    @NotNull(message = "Numero não pode ser vazio")
    @Max(value = 100)
    @Min(value = 1)
    private Integer numero;

    @Size(min = 3, max = 254, message = "Complemento deve ter no mínimo 3 letras")
    private String complemento;

    @NotNull(message = "CEP não pode ser vazio")
    @Size(min = 8, max = 8)
    @NotBlank
    private String cep;

    @NotNull(message = "Bairro não pode ser vazio")
    @NotBlank
    @Size(min = 3, max = 254, message = "Bairro deve ter no mínimo 3 letras")
    private String bairro;

    @NotNull(message = "Cidade não pode ser vazio")
    @Size(min = 3, max = 254, message = "Cidade deve ter no mínimo 3 letras")
    @NotBlank
    private String cidade;

    @NotNull(message = "UF não pode ser vazio")
    @NotBlank
    @Size(min = 2, max = 2, message = "A UF deve ter apenas 2 letras")
    private String uf;



}
