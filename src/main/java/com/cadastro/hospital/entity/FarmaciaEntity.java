package com.cadastro.hospital.entity;

import com.cadastro.hospital.enums.ClassificacaoRemedio;
import com.cadastro.hospital.enums.StatusFarmacia;
import com.cadastro.hospital.enums.TipoRemedio;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "FARMACIA")
public class FarmaciaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FARMACIA_SEQUENCE")
    @SequenceGenerator(name = "FARMACIA_SEQUENCE", sequenceName = "seq_farmacia", allocationSize = 1)
    @Column(name = "ID_FARMACIA")
    private Integer idFarmacia;

    @Column(name = "NOME_REMEDIO")
    private String nomeRemedio;

    @Column(name = "QUANTIDADE_LOTE")
    private Integer quantidadeLote;

    @Column(name = "GRAMATURA")
    private String gramatura;

    @Column(name = "DESCRIÇAO")
    private String descricao;

    @Column(name = "MARCA")
    private String marca;

    @Column(name = "INDICAÇÕES")
    private String indicacao;

    @Column(name = "TIPO_REMEDIO")
    @Enumerated(EnumType.STRING)
    private TipoRemedio tipoRemedio;

    @Column(name = "CLASSIFICACAO")
    @Enumerated(EnumType.STRING)
    private ClassificacaoRemedio classificacaoRemedio;

    @Column(name = "DATA_PEDIDO")
    private LocalDate dataPedido;

    @Column(name = "STATUS_FARMACIA")
    @Enumerated(EnumType.STRING)
    private StatusFarmacia statusFarmacia;

    @Column(name = "DATA_ENTREGA")
    private LocalDate dataEntrega;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "ID_FUNCIONARIO", referencedColumnName = "ID_FUNCIONARIO")
    private FuncionarioEntity funcionarioFarmacia;
}
