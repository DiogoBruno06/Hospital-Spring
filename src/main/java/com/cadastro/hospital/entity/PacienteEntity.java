package com.cadastro.hospital.entity;

import com.cadastro.hospital.enums.EntradaConsulta;
import com.cadastro.hospital.enums.Sexo;
import com.cadastro.hospital.enums.StatusPaciente;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "PACIENTEENTITY")
public class PacienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PACIENTE_SEQUENCE")
    @SequenceGenerator(name = "PACIENTE_SEQUENCE", sequenceName = "seq_paciente", allocationSize = 1)
    @Column(name = "ID_PACIENTE")
    private Integer idPaciente;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "SOBRENOME")
    private String sobrenome;

    @Column(name = "DATA_NASCIMENTO")
    private LocalDate dataNascimento;

    @Column(name = "CPF")
    private String cpf;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "SEXO")
    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @Column(name = "ENTRADA_CONSULTA")
    private EntradaConsulta entradaConsulta;

    @Column(name = "STATUS_PACIENTE")
    private StatusPaciente statusPaciente;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "PACIENTE_CARGO",
            joinColumns = @JoinColumn(name = "ID_PACIENTE"),
            inverseJoinColumns = @JoinColumn(name = "ID_CARGO")
    )
    private Set<CargoEntity> cargos = new HashSet<>();

    public void addCargo(CargoEntity cargoEntity) {
        this.cargos.add(cargoEntity);
    }
}
