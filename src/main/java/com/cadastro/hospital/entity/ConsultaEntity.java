package com.cadastro.hospital.entity;

import com.cadastro.hospital.enums.TipoConsulta;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "CONSULTAENTITY")
public class ConsultaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONSULTA_SEQUENCE")
    @SequenceGenerator(name = "CONSULTA_SEQUENCE", sequenceName = "seq_consulta", allocationSize = 1)
    @Column(name = "ID_CONSULTA")
    private Integer idConsulta;

    @Column(name = "AREA_CONSULTA")
    @Enumerated(EnumType.STRING)
    private TipoConsulta tipoConsulta;

    @Column(name = "DATA_CONSULTA")
    private LocalDate dataConsulta;

    @Column(name = "SINTOMAS")
    private String sintomas;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ID_FUNCIONARIO", referencedColumnName = "ID_FUNCIONARIO")
    private FuncionarioEntity funcionarioConsulta;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "ID_PACIENTE", referencedColumnName = "ID_PACIENTE")
    private PacienteEntity pacienteConsulta;
}
