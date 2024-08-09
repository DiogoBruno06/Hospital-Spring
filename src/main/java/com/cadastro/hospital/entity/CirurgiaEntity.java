package com.cadastro.hospital.entity;

import com.cadastro.hospital.enums.TipoCirurgia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "CIRURGIA")
public class CirurgiaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CIRURGIA_SEQUENCE")
    @SequenceGenerator(name = "CIRURGIA_SEQUENCE", sequenceName = "seq_cirurgia", allocationSize = 1)
    @Column(name = "ID_CIRURGIA")
    private Integer idCirurgia;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "CIRURGIA_FUNCIONARIO",
            joinColumns = @JoinColumn(name = "ID_CIRURGIA"),
            inverseJoinColumns = @JoinColumn(name = "ID_FUNCIONARIO")
    )
    @Column(name = "FUNCIONARIOS")
    private Set<FuncionarioEntity> cirurgiaFuncionarios = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "ID_PACIENTE", referencedColumnName = "ID_PACIENTE")
    private PacienteEntity cirurgiaPaciente;

    @Column(name = "DATA_CIRURGIA")
    private LocalDate dataCirurgia;

    @Column(name = "TIPO_CIRURGIA")
    private TipoCirurgia tipoCirurgia;

    public void addFuncionarios(FuncionarioEntity funcionarioEntity){
        this.cirurgiaFuncionarios.add(funcionarioEntity);
    }
}
