package com.cadastro.hospital.entity;

import com.cadastro.hospital.enums.TipoRemedio;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "SAIDA_REMEDIO")
public class RemedioSaidaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REMEDIO_SEQUENCE")
    @SequenceGenerator(name = "REMEDIO_SEQUENCE", sequenceName = "seq_remedio", allocationSize = 1)
    @Column(name = "ID_REMEDIO")
    private Integer idRemedio;

    @Column(name = "NOME_REMEDIO")
    private String nomeRemedio;

    @Column(name = "QUANTIDADE")
    private Integer quantidade;

    @Column(name = "DATA_PEDIDO")
    private Date saidaRemedio;

    @Column(name = "TIPO_REMEDIO")
    @Enumerated(EnumType.STRING)
    private TipoRemedio tipoRemedio;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ID_FUNCIONARIO", referencedColumnName = "ID_FUNCIONARIO")
    private FuncionarioEntity funcionarioRemedio;

}
