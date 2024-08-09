package com.cadastro.hospital.dto.Farmacia;

import com.cadastro.hospital.enums.ClassificacaoRemedio;
import com.cadastro.hospital.enums.TipoRemedio;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class FarmaciaCreateDTO {
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Nome do Remedio deve conter apenas letras, números e espaços")
    @Size(min = 2, max = 255)
    @Schema(description = "Nome do Remedio", required = true, example = "Buzonid")
    private String nomeRemedio;

    @NotNull
    @Positive
    @Schema(description = "Quantidade de Remedio para o Pedido", required = true, example = "2")
    private Integer quantidadeLote;

    @NotNull
    @Pattern(regexp = "\\d+", message = "Gramatura deve conter apenas números")
    @Schema(description = "Gramatura", required = true, example = "2")
    private String gramatura;

    @NotNull
    @Size(max = 255)
    @Schema(description = "Descrição do Remédio", required = true, example = "Este remédio é utilizado para...")
    private String descricao;

    @NotNull
    @Size(max = 100)
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Marca do Remedio deve conter apenas letras, números e espaços")
    @Schema(description = "Marca do Remedio", required = true, example = "Cimed")
    private String marca;

    @NotNull
    @Size(max = 255)
    @Schema(description = "Indicações de Uso", required = true, example = "Tomar um comprimido duas vezes ao dia")
    private String indicacoes;

    @NotNull
    @Schema(description = "Tipo de Remedio", required = true, example = "COMPRIMIDO")
    private TipoRemedio tipoRemedio;

    @NotNull
    @Schema(description = "Classificação do remédio", required = true, example = "ADULTO")
    private ClassificacaoRemedio classificacaoRemedio;
}
